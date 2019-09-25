package com.briup.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserReport {
	private Long id;
	private int state;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date reportDate;
	private String reportType;
	private String reportContent; 
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date processDate;
	private String processContent; 
	
	private User user;
	private Article article;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public Date getProcessDate() {
		return processDate;
	}
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
	public String getProcessContent() {
		return processContent;
	}
	public void setProcessContent(String processContent) {
		this.processContent = processContent;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	@Override
	public String toString() {
		return "UserReport [id=" + id + ", state=" + state + ", reportDate=" + reportDate + ", reportType=" + reportType
				+ ", reportContent=" + reportContent + ", processDate=" + processDate + ", processContent="
				+ processContent + ", user=" + user.getNickname() + ", article=" + article.getTitle() + "]";
	}
}
