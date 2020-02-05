package com.yjw.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 资源配置
 * 		自动读取 spring 读取的 properties 文件
 * @author eason
 *
 * 2016年5月30日下午2:45:56
 */
@Component("sysProperties")
public class SystemProperties {
	private String imgUploadUrl;  // 图片上传的物理地址
	private List<String> productCompress;  // 产品图片上传压缩尺寸
	private String productImgViewCompress;  // 产品管理上传完成之后的预览尺寸
	private String productImgUrl;  // 上传图片上传地址
	
	public String getImgUploadUrl() {
		return imgUploadUrl;
	}
	@Value("${system.imgUploadUrl}")
	public void setImgUploadUrl(String imgUploadUrl) {
		this.imgUploadUrl = imgUploadUrl;
	}
	public List<String> getProductCompress() {
		return productCompress;
	}
	@Value("${system.productCompress}")
	public void setProductCompress(String productCompress) {
		this.productCompress = new ArrayList<String>();
		for(String size : productCompress.split(",")) {
			this.productCompress.add(size);
		}
	}
	public String getProductImgViewCompress() {
		return productImgViewCompress;
	}
	@Value("${product.imgViewCompress}")
	public void setProductImgViewCompress(String productImgViewCompress) {
		this.productImgViewCompress = productImgViewCompress;
	}
	public String getProductImgUrl() {
		return productImgUrl;
	}
	@Value("${product.imgUrl}")
	public void setProductImgUrl(String productImgUrl) {
		this.productImgUrl = productImgUrl;
	}
}
