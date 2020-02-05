package com.yjw.pojo;

import java.util.Date;

/**
 * 订单pojo
 * @author eason
 *
 * 2016年6月6日下午4:15:47
 */
public class Order {
	private String id;  // 订单编号
	private int brandId;  // 品牌ID
	private String brandName;  // 品牌名称
	private int modelId;  // 机型ID
	private String modelName;  // 机型名称
	private double price;  // 价格
	/**
	 * 订单状态 
	 * 		0: 订单提交  1: 订单支付中  2: 订单已支付  3: 已发货  4: 已收货  5: 已评价  
	 * 		-1: 订单提交-取消  -2: 申请退款  -3: 退款中  -4: 退款成功并取消  -5: 申请退货  -6: 退货中  -7: 退货成功并取消  
	 * 		-102: 申请退货失败  -101: 申请退款失败
	 */
	private int state;  // 订单状态
	private String logistics;  // 物流单号
	private String user;  // 下单用户
	private String userCname;  //  下单人称呼
	private String phone;  // 下单人联系方式
	private String userMsg;  // 用户评价
	private Date createTime;
	private Date updateTime;
	private String updator;  // 变更人(包括  普通用户和后台用户)
	private String info;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getLogistics() {
		return logistics;
	}
	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUserMsg() {
		return userMsg;
	}
	public void setUserMsg(String userMsg) {
		this.userMsg = userMsg;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getUserCname() {
		return userCname;
	}
	public void setUserCname(String userCname) {
		this.userCname = userCname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
