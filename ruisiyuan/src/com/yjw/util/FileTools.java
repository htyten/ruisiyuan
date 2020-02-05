package com.yjw.util;

import java.io.File;

import net.coobird.thumbnailator.Thumbnails;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件工具类
 * @author 姚嘉伟
 *
 * 2014-6-10下午02:04:14
 */
public class FileTools {
	
	/**
	 * 保存上传文件
	 * @param path			文件路径
	 * @param fileName		文件名称
	 * @param file			上传的文件
	 * @throws Exception 
	 */
	public static File saveFile(String path, String fileName, MultipartFile file) throws Exception {
		// 创建目录
		CreateFileUtil.createDir(path);
		// 创建文件
		CreateFileUtil.CreateFile(path + "/" + fileName);
		File f = new File(path + "/" + fileName);
		file.transferTo(f);  // 保存上传的文件
		return f;
	}
	
	/**
	 * 压缩图片
	 * @param imgPath				被压缩图片url
	 * @param compressionImgPath	压缩图片url
	 * @param width					压缩宽
	 * @param height				压缩高
	 * @return						被压缩的文件
	 * @throws Exception 
	 */
	public static File compressionImg(String imgPath, String compressionImgPath, int width, int height) throws Exception {
		CreateFileUtil.CreateFile(compressionImgPath);
		Thumbnails.of(imgPath)
				.size(width, height)
				.toFile(compressionImgPath);
		return new File(compressionImgPath);
	}
	
}
