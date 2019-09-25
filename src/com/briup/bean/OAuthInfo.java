package com.briup.bean;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 第三方平台的登录信息，不同平台需要有不同的OAuthInfo对象
 */
public abstract class OAuthInfo {
	private String authUrl;
	private String tokenUrl;
	private String userInfoApi;
	//头像路径
	private String portraitUrlTemplate;
	
	private Map<String,String> params = new HashMap<>();
	
	/**
	 * 构造时读取xml配置文件获取基本信息
	 */
	public OAuthInfo(String configFile) {
		System.out.println("解析的文件："+configFile);
		SAXReader reader = new SAXReader();
		try {
			//params属性赋值
			Document document = reader.read(configFile);
			Element configElement = document.getRootElement();
			Element paramsElement = configElement.element("params");
			List<Element> paramElements = paramsElement.elements();
			for(Element paramElement : paramElements){
				params.put(paramElement.attributeValue("name"),
						paramElement.attributeValue("value"));
			}
			//System.out.println("解析后的内容:"+params);
			//其他几个属性赋值需要进行第一步处理
			authUrl = replaceUriHandler(configElement.element("authUrl").getText().trim());
			tokenUrl = replaceUriHandler(configElement.element("tokenUrl").getText().trim());
			userInfoApi = replaceUriHandler(configElement.element("userInfoApi").getText().trim());
			portraitUrlTemplate = replaceUriHandler(configElement.element("portraitUrlTemplate").getText().trim());
			
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过正则表达式对字符串进行处理，将params中已经包含的信息注入四个url中，生成第一步url
	 */
	public String replaceUriHandler(String url) {
		//执行匹配操作
		Pattern regex = Pattern.compile("\\$\\{(.+?)\\}");
		Matcher matcher = regex.matcher(url);
		//缓存替换后的结果
		StringBuffer sb = new StringBuffer();
		while(matcher.find()) {
			//找到${XXX}中的XXX
			String paramKey = matcher.group(1);
			//根据XXX找到已解析出的value值
			String replaceVal = params.get(paramKey);
			//System.out.println("要替换的:"+paramKey+": "+replaceVal);
			if(replaceVal==null) {
				//没有找到可替换的value的时候跳过，如第一次找不到code，accessToken
				continue;
			}else {
				//使用指定值替换匹配部分，并将替换后的从字符串首部开始的内容保存到sb
				matcher.appendReplacement(sb, replaceVal);
			}
		}
		//最后一个匹配项替换完成，sb只存储到最后匹配替换成功后到的位置
		//最后匹配项右边的部分需要调用方法添加进去
		matcher.appendTail(sb);
		//System.out.println("替换结束:"+sb.toString());
		return sb.toString();
	}
	
	/**
	 * 从返回的json对象中获取User对象，不同平台返回的json信息不同
	 * @param userNode
	 * @return
	 * @throws IOException
	 */
	public abstract User getUser(JsonNode userNode) throws IOException;
	
	/**
	 * 验证授权服务器返回的user信息是否有效(需要的参数是否传回来了)
	 * @param userNode
	 * @return
	 * @throws IOException
	 */
	public abstract boolean UserDataValidate(JsonNode userNode) throws IOException;

	/**
	 * 引导用户浏览器跳转到授权页的完整请求路径，第一个请求
	 * @return
	 */
	public String getAuthUrl() {
		return authUrl;
	}
	
	/**
	 * 携带code获取access token的完整请求路径，第二个请求
	 * @param code
	 * @return
	 */
	public String getTokenUrl(String code) {
		params.put("code", code);
		//不能替换，要将这些带有变值的url在每次请求的时候都做真实值的替换，如code的值，防止第二次请求的问题
		//否则第二次请求还是使用的第一次请求的code
		//tokenUrl = replaceUriHandler(tokenUrl);
		return replaceUriHandler(tokenUrl);
	}
	
	/**
	 * 携带access token获取用户信息的完整请求路径，第三个请求
	 * @param accessToken
	 * @return
	 */
	public String getUserInfoApi(String accessToken) {
		params.put("accessToken", accessToken);
//		userInfoApi = replaceUriHandler(userInfoApi);
		return replaceUriHandler(userInfoApi);
	}

	/**
	 * 下载头像图片，头像是网络上的一张图片
	 * @param portrait
	 * @return
	 */
	public byte[] downloadPortrait(String portrait) {
		params.put("portrait", portrait);
		String portraitUrlTemplateTemp = replaceUriHandler(portraitUrlTemplate);
		
		//生成请求，发送请求申请头像资源，下载保存
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			URL url = new URL(portraitUrlTemplateTemp);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			byte[] bys = new byte[1024];
			int cnt = -1;
			while((cnt=is.read(bys))!=-1) {
				bos.write(bys, 0, cnt);
			}
			
			is.close();
			conn.disconnect();
			bos.close();
			return bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
