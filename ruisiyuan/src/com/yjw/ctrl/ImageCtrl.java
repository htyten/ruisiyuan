package com.yjw.ctrl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yjw.pojo.Account;
import com.yjw.pojo.Image;
import com.yjw.service.ImageService;
import com.yjw.util.SystemProperties;

/**
 * 图片上传
 * @author eason
 *
 * 2016年5月30日下午2:43:13
 */
@Controller
@RequestMapping("image")
public class ImageCtrl extends BaseCtrl<Image> {
	
	@Autowired
	private ImageService imageService;
	@Autowired
	private SystemProperties sysProperties;

	@RequestMapping("upload")
	@ResponseBody
	public Map<String, Object> upload(HttpServletRequest req, ModelMap model, 
			@RequestParam(value = "file", required = false) MultipartFile file) {
		// 获取登录用户
		Account account = (Account) model.get("loginAccount");
		// 上传图片并获得上传的图片名
		String fileName = imageService.upload(sysProperties.getImgUploadUrl(), 
				sysProperties.getProductCompress(), 
				file, account.getAccount());
		log.info("图片上传成功后的真实文件名：" + fileName);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("path", sysProperties.getProductImgUrl() + "/" + fileName);  // 设置访问地址
		return result;
	}
}
