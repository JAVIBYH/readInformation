package com.briup.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 资讯类
 * @author Administrator
 *
 */
public class Article {
	//ID
	private Long id;
	//标题
	private String title;
	//内容
	private String content;
	//发布日期
	private Date releaseDate;
	//点击量
	private int clickTimes;
	//状态0:未审核，1已审核通过，2审核不通过，3，通过被举报为不通过，4已删除
	private int state;
	//简介
	private String summary;
	//背景音乐路径
	private String backgroundMusicUrl;
	//视频资讯路径
	private String videoUrl;
	//封面图片路径，最多三张
	private String pictureUrls;
	//类型，0：普通图文，1：纯视频资讯
	private int type;
	//发布者
	private User user;
	//所属栏目
	private Category category;
	//点赞数
	private int likeTimes;
	//收藏数
	private int collectionTimes;
	//举报数
	private int reportTimes;
	//是否点赞 0:未点赞，1：已点赞，2：点赞后取消
	private int isLike;
	//是否收藏0:未收藏，1：已收藏，3：收藏后取消
	private int isCollection;
	//是否举报0:未举报，1：已举报未处理，2,3,4,5,6：举报已处理
	private int isReport;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public int getClickTimes() {
		return clickTimes;
	}
	public void setClickTimes(int clickTimes) {
		this.clickTimes = clickTimes;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getBackgroundMusicUrl() {
		return backgroundMusicUrl;
	}
	public void setBackgroundMusicUrl(String backgroundMusicUrl) {
		this.backgroundMusicUrl = backgroundMusicUrl;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getPictureUrls() {
		return pictureUrls;
	}
	public List<String> getPictureUrlsList() {
		if(pictureUrls != null) {
			return Arrays.asList(pictureUrls.split(","));
		}
		return null;
	}
	public void setPictureUrls(String pictureUrls) {
		if(this.pictureUrls != null) {
			this.pictureUrls += ","+pictureUrls;
		}else{
			this.pictureUrls = pictureUrls;
		}
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getLikeTimes() {
		return likeTimes;
	}
	public void setLikeTimes(int likeTimes) {
		this.likeTimes = likeTimes;
	}
	public int getCollectionTimes() {
		return collectionTimes;
	}
	public void setCollectionTimes(int collectionTimes) {
		this.collectionTimes = collectionTimes;
	}
	public int getReportTimes() {
		return reportTimes;
	}
	public void setReportTimes(int reportTimes) {
		this.reportTimes = reportTimes;
	}
	public int getIsLike() {
		return isLike;
	}
	public void setIsLike(int isLike) {
		this.isLike = isLike;
	}
	public int getIsCollection() {
		return isCollection;
	}
	public void setIsCollection(int isCollection) {
		this.isCollection = isCollection;
	}
	public int getIsReport() {
		return isReport;
	}
	public void setIsReport(int isReport) {
		this.isReport = isReport;
	}
	/*@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", Content="  + ", releaseDate=" + releaseDate
				+ ", clickTimes=" + clickTimes + ", state=" + state + ", summary="  + ", backgroundMusicUrl="
				+ backgroundMusicUrl + ", videoUrl=" + videoUrl + ", pictureUrls=" + pictureUrls + ", type=" + type
				+ ", user=" + user.getNickname() + ", category=" + category.getName() + ", likeTimes=" + likeTimes + ", collectionTimes="
				+ collectionTimes + ", reportTimes=" + reportTimes + ", isLike=" + isLike + ", isCollection="
				+ isCollection + ", isReport=" + isReport + "]";
	}*/
}
