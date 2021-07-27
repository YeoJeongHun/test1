package com.ex1.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.ex1.demo.dto.Member;
import com.ex1.demo.dto.Rq;
import com.ex1.demo.util.Util;

@Controller
public class MpaUsrHomeController {
	@RequestMapping("/")
	public String showMainRoot() {
		return "redirect:/mpaUsr/home/main";
	}

	@RequestMapping("/mpaUsr/home/main")
	public String showMain() {
		return "mpaUsr/home/main";
	}

	// 아래부터 테스트용
	
	@RequestMapping("/mpaUsr/home/uploadFile")
	public String uploadFile(HttpServletRequest req, MultipartRequest multipartRequest) {
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		Member loginedMember = ((Rq) req.getAttribute("rq")).getLoginedMember();
		MultipartFile multipartFile1 = fileMap.get("input__file");
		req.setAttribute("testtest", multipartFile1.getSize());
				
		for (String fileInputName : fileMap.keySet()) {
			MultipartFile multipartFile = fileMap.get("input__file");
			
			req.setAttribute("test", multipartFile.getSize());
			
			//String fileExt = Util.getFileExtFromFileName(multipartFile.getOriginalFilename()).toLowerCase();
			
			// 새 파일이 저장될 폴더(io파일) 객체 생성
	        String targetDirPath = "C:/work/demo/file/profile/member__id__" + loginedMember.getId();
	        java.io.File targetDir = new java.io.File(targetDirPath);

	        // 새 파일이 저장될 폴더가 존재하지 않는다면 생성
	        if (targetDir.exists() == false) {
	            targetDir.mkdirs();
	        }
	        
	        String targetFileName = multipartFile.getOriginalFilename();
	        String targetFilePath = targetDirPath + "/" + targetFileName;

	        // 파일 생성(업로드된 파일을 지정된 경로롤 옮김)
	        try {
	            multipartFile.transferTo(new File(targetFilePath));
	        } catch (IllegalStateException | IOException e) {
	            return "mpaUsr/home/main";
	        }

			Util.ImageResizeForProfileImg(targetDirPath, targetFilePath, 250,250);
		}

		return "mpaUsr/home/main";
	}

}
