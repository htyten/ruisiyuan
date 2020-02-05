package com.yjw.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传服务
 * @author eason
 *
 * 2016年5月30日下午2:48:24
 */
public interface ImageService {
	/**
	 * 文件上传
	 * @param path		图片上传物理地址
	 * @param compress	图片压缩尺寸集合
	 * @param file		图片
	 * @param creator	上传人
	 * @return			访问的图片名
	 */
	public String upload(String path, List<String> compress, MultipartFile file, String creator);
}
