package com.yjw.service.impl;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yjw.dao.ImageDAO;
import com.yjw.pojo.Image;
import com.yjw.service.ImageService;
import com.yjw.util.FileTools;

@Service
public class ImageServiceImpl implements ImageService {
	
	private Log log = LogFactory.getLog(ImageServiceImpl.class);
	
	@Autowired
	private ImageDAO imgDAO;

	@Override
	public String upload(String path, List<String> compress, MultipartFile file, String creator) {
		// 获取文件名
		String fileName = file.getOriginalFilename();
		// 生成实际图片文件名
		String randomFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
		try {
			// 保存上传的原图
			File f = FileTools.saveFile(path, randomFileName, file);
			// 压缩尺寸
			for(String size : compress) {
				// 压缩图片路径生成
				String smallImgPath = path + "/" + size + "/" + randomFileName;
				FileTools.compressionImg(f.getPath(), 
						smallImgPath, 
						Integer.parseInt(size.split("_")[0]), 
						Integer.parseInt(size.split("_")[1]));
			}
			// 存储DB
			Image img = new Image();
			img.setCreator(creator);
			img.setImgFormat(f.getName().split("\\.")[f.getName().split("\\.").length - 1]);
			img.setName(f.getName());
			img.setSize(f.length());
			imgDAO.add(img);
			// 返回图片访问地址
			return "/567_300/" + randomFileName;
		} catch (Exception e) {
			log.error("图片上传失败：" + e.getMessage());
		}
		return null;
	}
	
}
