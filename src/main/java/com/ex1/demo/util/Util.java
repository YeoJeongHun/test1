package com.ex1.demo.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.ex1.demo.dto.Member;
import com.ex1.demo.dto.Rq;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
    public static String getNowDateStr() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = (Date) new java.util.Date();
        return format1.format(time);
    }

    public static Map<String, Object> mapOf(Object... args) {
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("인자를 짝수개 입력해주세요.");
        }

        int size = args.length / 2;

        Map<String, Object> map = new LinkedHashMap<>();

        for (int i = 0; i < size; i++) {
            int keyIndex = i * 2;
            int valueIndex = keyIndex + 1;

            String key;
            Object value;

            try {
                key = (String) args[keyIndex];
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("키는 String으로 입력해야 합니다. " + e.getMessage());
            }

            value = args[valueIndex];

            map.put(key, value);
        }

        return map;
    }

    public static int getAsInt(Object object, int defaultValue) {
        if (object instanceof BigInteger) {
            return ((BigInteger) object).intValue();
        } else if (object instanceof Double) {
            return (int) Math.floor((double) object);
        } else if (object instanceof Float) {
            return (int) Math.floor((float) object);
        } else if (object instanceof Long) {
            return (int) object;
        } else if (object instanceof Integer) {
            return (int) object;
        } else if (object instanceof String) {
            return Integer.parseInt((String) object);
        }

        return defaultValue;
    }

    public static String msgAndBack(String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert('" + msg + "');");
        sb.append("history.back();");
        sb.append("</script>");

        return sb.toString();
    }

    public static String msgAndReplace(String msg, String uri) {
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert('" + msg + "');");
        sb.append("location.replace('" + uri + "');");
        sb.append("</script>");

        return sb.toString();
    }

    public static String toJsonStr(Map<String, Object> param) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(param);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static Map<String, String> getParamMap(HttpServletRequest request) {
        Map<String, String> param = new HashMap<>();

        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);

            param.put(paramName, paramValue);
        }

        return param;
    }

    public static String getUriEncoded(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            return str;
        }
    }

    public static <T> T ifNull(T data, T defaultValue) {
        return data != null ? data : defaultValue;
    }

    public static <T> T reqAttr(HttpServletRequest req, String attrName, T defaultValue) {
        return (T) ifNull(req.getAttribute(attrName), defaultValue);
    }

    public static boolean isEmpty(Object data) {
        if (data == null) {
            return true;
        }

        if (data instanceof String) {
            String strData = (String) data;

            return strData.trim().length() == 0;
        } else if (data instanceof List) {
            List listData = (List) data;

            return listData.isEmpty();
        } else if (data instanceof Map) {
            Map mapData = (Map) data;

            return mapData.isEmpty();
        }

        return false;
    }

    public static <T> T ifEmpty(T data, T defaultValue) {
        if (isEmpty(data)) {
            return defaultValue;
        }

        return data;
    }

    public static String getFileExtTypeCodeFromFileName(String fileName) {
        String ext = getFileExtFromFileName(fileName).toLowerCase();

        switch (ext) {
            case "jpeg":
            case "jpg":
            case "gif":
            case "png":
                return "img";
            case "mp4":
            case "avi":
            case "mov":
                return "video";
            case "mp3":
                return "audio";
        }

        return "etc";
    }

    public static String getFileExtType2CodeFromFileName(String fileName) {
        String ext = getFileExtFromFileName(fileName).toLowerCase();

        switch (ext) {
            case "jpeg":
            case "jpg":
                return "jpg";
            case "gif":
                return ext;
            case "png":
                return ext;
            case "mp4":
                return ext;
            case "mov":
                return ext;
            case "avi":
                return ext;
            case "mp3":
                return ext;
        }

        return "etc";
    }

    public static String getFileExtFromFileName(String fileName) {
        int pos = fileName.lastIndexOf(".");
        String ext = fileName.substring(pos + 1);

        return ext;
    }

    public static String getNowYearMonthDateStr() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy_MM");

        String dateStr = format1.format(System.currentTimeMillis());

        return dateStr;
    }

    public static List<Integer> getListDividedBy(String str, String divideBy) {
        return Arrays.asList(str.split(divideBy)).stream().map(s -> Integer.parseInt(s.trim()))
                .collect(Collectors.toList());
    }

    public static boolean delteFile(String filePath) {
        java.io.File ioFile = new java.io.File(filePath);
        if (ioFile.exists()) {
            return ioFile.delete();
        }

        return true;
    }

    public static String numberFormat(int num) {
        DecimalFormat df = new DecimalFormat("###,###,###");

        return df.format(num);
    }

    public static String numberFormat(String numStr) {
        return numberFormat(Integer.parseInt(numStr));
    }

    public static boolean allNumberString(String str) {
        if (str == null) {
            return false;
        }

        if (str.length() == 0) {
            return true;
        }

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }

        return true;
    }

    public static boolean startsWithNumberString(String str) {
        if (str == null) {
            return false;
        }

        if (str.length() == 0) {
            return false;
        }

        return Character.isDigit(str.charAt(0));
    }

    public static boolean isStandardLoginIdString(String str) {
        if (str == null) {
            return false;
        }

        if (str.length() == 0) {
            return false;
        }

        // 조건
        // 5자 이상, 20자 이하로 구성
        // 숫자로 시작 금지
        // _, 알파벳, 숫자로만 구성
        return Pattern.matches("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,19}$", str);
    }

    public static String getNewUriRemoved(String uri, String paramName) {
        String deleteStrStarts = paramName + "=";
        int delStartPos = uri.indexOf(deleteStrStarts);

        if (delStartPos != -1) {
            int delEndPos = uri.indexOf("&", delStartPos);

            if (delEndPos != -1) {
                delEndPos++;
                uri = uri.substring(0, delStartPos) + uri.substring(delEndPos, uri.length());
            } else {
                uri = uri.substring(0, delStartPos);
            }
        }

        if (uri.charAt(uri.length() - 1) == '?') {
            uri = uri.substring(0, uri.length() - 1);
        }

        if (uri.charAt(uri.length() - 1) == '&') {
            uri = uri.substring(0, uri.length() - 1);
        }

        return uri;
    }

    public static String getNewUri(String uri, String paramName, String paramValue) {
        uri = getNewUriRemoved(uri, paramName);

        if (uri.contains("?")) {
            uri += "&" + paramName + "=" + paramValue;
        } else {
            uri += "?" + paramName + "=" + paramValue;
        }

        uri = uri.replace("?&", "?");

        return uri;
    }

    public static String getNewUriAndEncoded(String uri, String paramName, String pramValue) {
        return getUriEncoded(getNewUri(uri, paramName, pramValue));
    }

    public static String msgAndBack(HttpServletRequest req, String msg) {
        req.setAttribute("msg", msg);
        req.setAttribute("historyBack", true);
        return "mpaUsr/common/redirect";
    }

    public static String msgAndReplace(HttpServletRequest req, String msg, String replaceUri) {
        req.setAttribute("msg", msg);
        req.setAttribute("replaceUri", replaceUri);
        return "mpaUsr/common/redirect";
    }

    public static String getTempPassword(int length) {
        int index = 0;
        char[] charArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; i++) {
            index = (int) (charArr.length * Math.random());
            sb.append(charArr[index]);
        }

        return sb.toString();
    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (Exception ex) {
            return "";
        }
    }
    
    public static boolean existsProfile(int id) {
    	String targetDirPath = "C:/work/demo/file/profile/member__id__" + id + "/profileImg.jpg";
        java.io.File targetDir = new java.io.File(targetDirPath);
        
        // 해당 유저 프로필 폴더에 이미지가 존재하는지 확인
        if (targetDir.exists() == true) {
            return true;
        }
        return false;
    }
    
    public static String getProfilePass(int id) {
    	return "/file/profile/member__id__" + id + "/profileImg.jpg";
    }
    
    public static String getProfileDirPass(int id) {
    	return "/file/profile/member__id__" + id;
    }
    
    //https://unknownyun.tistory.com/20     <<참고
    public static void ImageResizeForProfileImg(String targetDirPath, String targetFilePath, int newWidth, int newHeigt) {
        try{
        	targetDirPath = targetDirPath + "/profileImg.jpg";
            //String imgTargetPath= "C:/work/demo/file/test_resize.jpg";    // 새 이미지 파일명
            String imgFormat = "jpg";                             // 새 이미지 포맷. jpg, gif 등
            
            // 원본 이미지 가져오기
            Image image = ImageIO.read(new File(targetFilePath));
             
            Image resizeImage = image.getScaledInstance(newWidth, newHeigt, Image.SCALE_SMOOTH);
  
            // 새 이미지  저장하기
            BufferedImage newImage = new BufferedImage(newWidth, newHeigt, BufferedImage.TYPE_INT_RGB);
            Graphics g = newImage.getGraphics();
            g.drawImage(resizeImage, 0, 0, null);
            g.dispose();
            ImageIO.write(newImage, imgFormat, new File(targetDirPath));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static String getUserProfileImg(HttpServletRequest req, MultipartRequest multipartRequest, int id) {
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		
		MultipartFile multipartFile = fileMap.get("input__file");
		
		if(multipartFile.getSize()>500000) {
			return "F-2";
		}
		
		// 새 파일이 저장될 폴더(io파일) 객체 생성
	       String targetDirPath = "C:/work/demo/file/profile/member__id__" + id;
	       java.io.File targetDir = new java.io.File(targetDirPath);

        // 새 파일이 저장될 폴더가 존재하지 않는다면 생성
        if (targetDir.exists() == false) {
            targetDir.mkdirs();
        }
	        
        String targetFileName = multipartFile.getOriginalFilename();
        String targetFilePath = targetDirPath + "/" + targetFileName;
        
        //파일 선택을 안했을 경우
        if(existsProfile(id)&&multipartFile.getSize()<1) {
        	return "S-3";	//기존에 이미 파일 존재함
        }
        else if(multipartFile.getSize()<1) {
			return setBasicProfileImg(targetDirPath, id);	//기본이미지로 셋팅해주기
		}

        // 파일 생성(업로드된 파일을 지정된 경로롤 옮김)
        try {
            multipartFile.transferTo(new File(targetFilePath));
        } catch (IllegalStateException | IOException e) {
            return "F-4";
        }
        try {
            Util.ImageResizeForProfileImg(targetDirPath, targetFilePath, 250,250);
        } catch (IllegalStateException e) {
            return "F-1";
        }
	        
        return "S-1";
	}
    
    public static String setBasicProfileImg(String targetDirPath, int id) {
    	try{
            String imgOriginalPath= "C:/work/demo/file/profile/basic.jpg";           // 기본 이미지 파일명
            //String imgTargetPath= "C:/work/demo/file/test_resize.jpg";    // 새 이미지 파일명
            String targetFilePath = targetDirPath + "/" + "profileImg.jpg";
            String imgFormat = "jpg";                             // 새 이미지 포맷. jpg, gif 등
            int newWidth = 250;                                  // 변경 할 넓이
            int newHeigt = 250;      
 
            // 원본 이미지 가져오기
            Image image = ImageIO.read(new File(imgOriginalPath));
               
            Image resizeImage = image.getScaledInstance(newWidth, newHeigt, Image.SCALE_SMOOTH);
  
            // 새 이미지  저장하기
            BufferedImage newImage = new BufferedImage(newWidth, newHeigt, BufferedImage.TYPE_INT_RGB);
            Graphics g = newImage.getGraphics();
            g.drawImage(resizeImage, 0, 0, null);
            g.dispose();
            ImageIO.write(newImage, imgFormat, new File(targetFilePath));
             
            return "S-2";
        }catch (Exception e){
            e.printStackTrace();
            return "F-3";
        }
	}
        
    @SuppressWarnings("null")
	public static Map<String, Object> getImgInfo(MultipartRequest multipartRequest, int id) {
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		Map<String, Object> map = null;
		for (String fileInputName : fileMap.keySet()) {
			MultipartFile multipartFile = fileMap.get(fileInputName);
			String targetFileName = multipartFile.getOriginalFilename();
			map.put("originFileName", targetFileName);
			map.put("fileExt", getFileExtFromFileName(multipartFile.getOriginalFilename()));
			map.put("fileSize", multipartFile.getSize());
			map.put("fileDir", getProfileDirPass(id));
		}
		return map;
	}
    
}
