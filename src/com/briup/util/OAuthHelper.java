package com.briup.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.briup.bean.OAuthInfo;
import com.briup.bean.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 授权处理的辅助工具类
 * 解析配置文件，解析所需的三个请求url构成
 * @author Administrator
 *
 */
public class OAuthHelper {
	
	//存储信息的数据结构
	private static Map<String,OAuthInfo> infos = new HashMap();
	//加载信息的过程
	static {
		//获取config路径目录
		String configBasePath = OAuthHelper.class.getClassLoader().getResource("../config").getPath();
		infos.put("baidu", new OAuthInfo(configBasePath+"baidu.xml") {
			
			@Override
			public User getUser(JsonNode userNode) throws IOException {
				User user = new User();
				user.setThirdId("baidu_"+userNode.get("uid").asText());
				user.setNickname(userNode.get("uname").asText());
				user.setPortraitData(downloadPortrait(userNode.get("portrait").asText()));
				return user;
			}
			
			@Override
			public boolean UserDataValidate(JsonNode userNode) throws IOException {
				return userNode.get("uid")!=null;
			}
		});
	}
	
	/**
	 * 获取初始解析的平台OAuthInfo对象
	 * @param oAuthType
	 * @return
	 */
	public static OAuthInfo getInfo(String oAuthType) {
		return infos.get(oAuthType);
	}
	
	/**
	 * 通过code发送第二个请求，获取accesstoken，通过accesstoken发送第三个请求获取user信息，封装User对象返回
	 */
	public static User getUser(String platName,String code) {
		OAuthInfo info = getInfo(platName);
		//使用code构成第二次请求url路径
		String tokenUrl = info.getTokenUrl(code);
		ObjectMapper mapper = new ObjectMapper();
		try {
			//携带code发送第二次请求，返回access token的值
			String httpGetRes = httpGet(tokenUrl);
			JsonNode resNode = mapper.readTree(httpGetRes);
			//System.out.println("第二次请求返回的结果resNode:"+httpGetRes);
			JsonNode accessTokenNode = resNode.get("access_token");
			if(accessTokenNode==null) {
				//System.out.println("第二次请求结果错误，没有值");
				return null;
			}else {
				//使用acess token构成第三次请求yrl路径
				String userInfoApiURL = info.getUserInfoApi(accessTokenNode.asText());
				//携带access token的值发动第三次请求，获取用户信息
				httpGetRes = httpGet(userInfoApiURL);
				JsonNode userNode = mapper.readTree(httpGetRes);
				//System.out.println("第三次请求返回的结果userNode:"+httpGetRes);
				if(info.UserDataValidate(userNode)) {
					return info.getUser(userNode);
				}else {
					//System.out.println("第三次请求结果错误，没有值");
					return null;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String httpGet(String urlStr) throws Exception {
		//System.out.println("****");
		//System.out.println(urlStr);
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.connect();
		InputStream is = conn.getInputStream();
		byte[] bs = new byte[is.available()];
		is.read(bs);
		String res = new String(bs, "UTF-8");
		
		//System.out.println(urlStr+"的结果:"+res);
		is.close();
		conn.disconnect();
		return res;
	}
	
	
}
