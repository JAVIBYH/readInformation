package com.briup.bean;

/**
 * 用户类
 *
 */
public class User {
	//用户ID
	private Long id;
	//用户登录账户
	private String account;
	//用户登录密码
	private String password;
	//用户昵称
	private String nickname;
	//用户性别
	private String gender;
	//用户年龄
	private int age;
	//用户邮箱
	private String email;
	//用户地址
	private String address;
	//用户角色 0管理员，1普通用户
	private int role;
	//用户头像图片
	private byte[] portraitData;
	//private String portraitUrl;
	//第三方平台账号ID
	private String thirdId;
	
	
	
	public User() {
	}
	
	public User(String account, String password, String nickname, String gender, int age, String email,
			String address) {
		this.account = account;
		this.password = password;
		this.nickname = nickname;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.address = address;
	}

	/**
	 * 用户头像文件名，用于页面显示
	 * 用户头像存储的文件名将与用户ID一致，否则显示默认头像
	 */
	public String getPortrait() {
		return "../dbuploadImage/"+id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	  
	public byte[] getPortraitData() {
		return portraitData;
	}

	public void setPortraitData(byte[] portraitData) {
		this.portraitData = portraitData;
	}

	public String getThirdId() {
		return thirdId;
	}
	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", password=" + password + ", nickname=" + nickname
				+ ", gender=" + gender + ", age=" + age + ", email=" + email + ", address=" + address + "]";
	}
	
}
