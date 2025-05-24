package com.cui.controller;

import com.cui.pojo.Result;
import com.sun.activation.registries.MailcapFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author: xuYuYu
 * @createTime: 2025/5/25 1:43
 * @Description: TODO
 */
@RestController
public class FileUploadController {
	@PostMapping("/upload")
	public Result<String> fileUpload(MultipartFile file) throws IOException {
		//获取文件源名字
		String originalFileName= file.getOriginalFilename();
		//保证文件名是唯一的，从而防止文件被覆盖
		String filename = UUID.randomUUID().toString()+originalFileName.substring(originalFileName.lastIndexOf("."));
		//存到本地
		file.transferTo(new File("C:\\Users\\XYC\\Desktop\\files)"+filename));
		return Result.success("url");
	}
}
