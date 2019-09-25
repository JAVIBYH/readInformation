package com.briup.bean;

import java.util.Arrays;

public class ImgResult {
	private int  errno;//错误代码
    private String[] data;//存放上传的图片的路径
    
	public int getErrno() {
		return errno;
	}
	public void setErrno(int errno) {
		this.errno = errno;
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ImgResult [errno=" + errno + ", data=" + Arrays.toString(data) + "]";
	}
}
