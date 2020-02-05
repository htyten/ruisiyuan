package com.yjw.pojo;

import java.util.Date;

/**
 * 图片管理
 * @author eason
 *
 * 2016年5月30日下午2:44:33
 */
public class Image {
	private int id;
	private String name;
	private String imgFormat;  // 图片格式
	private long size;  // 图片大小
	private String creator;
	private Date createTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgFormat() {
		return imgFormat;
	}
	public void setImgFormat(String imgFormat) {
		this.imgFormat = imgFormat;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
