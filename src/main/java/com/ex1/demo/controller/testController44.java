package com.ex1.demo.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ex1.demo.dto.testSet;

@Controller
public class testController44 {
	
	@GetMapping("/test")
	@ResponseBody
	public String test() {
		StringBuffer result = new StringBuffer();
        System.out.println("1aaa");
        String jsonPrintString = null;
        try {
			String apiUrl = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?"+
					"serviceKey=8L1tZMt%2BbiMMfFRIEd3sBPwysZ8yFmWBfLhSBF%2B4JzS3MEXtPNzPW01Yt7u8GORzjEHKU0bMnEX3SAwojbT7Ag%3D%3D"+
					"&pageNo=1"+
					"&numOfRows=10"+
					"&startCreateDt=20210901"+
					"&endCreateDt=20210901";
			URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            String returnLine;
            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine);
            }

            //JSONObject jsonObject = XML.toJSONObject(result.toString());
            org.json.JSONObject jsonObject = XML.toJSONObject(result.toString());
            jsonPrintString = jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonPrintString;
    }
	
	@GetMapping("/mpaUsr/home/testPage")
    public String callApiWithJson(HttpServletRequest req) {
        StringBuffer result = new StringBuffer();
        String jsonPrintString = null;
        try {
            String apiUrl = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?"+
					"serviceKey=8L1tZMt%2BbiMMfFRIEd3sBPwysZ8yFmWBfLhSBF%2B4JzS3MEXtPNzPW01Yt7u8GORzjEHKU0bMnEX3SAwojbT7Ag%3D%3D"+
					"&pageNo=1"+
					"&numOfRows=10"+
					"&startCreateDt=20210901"+
					"&endCreateDt=20210901";
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            String returnLine;
            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine);
            }

            JSONObject jsonObject = XML.toJSONObject(result.toString());
            jsonPrintString = jsonObject.toString();
            System.out.println(jsonObject);
            System.out.println(jsonPrintString);
            
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("jsonPrintString", jsonPrintString);
        
        
        return "mpaUsr/home/testPage";
    }
	
	@RequestMapping("/mpaUsr/home/testPage1")
	public String test1aa(HttpServletRequest req) {
		String url = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson";
		String serviceKey="?serviceKey=8L1tZMt%2BbiMMfFRIEd3sBPwysZ8yFmWBfLhSBF%2B4JzS3MEXtPNzPW01Yt7u8GORzjEHKU0bMnEX3SAwojbT7Ag%3D%3D";
		String pageNo = "&pageNo=1";
		String numOfRows = "&numOfRows=10";
		String startCreateDt = "&startCreateDt=20210902";
		String endCreateDt = "&endCreateDt=20210902";
		String link = url+serviceKey+pageNo+numOfRows+startCreateDt+endCreateDt;
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder doc = factory.newDocumentBuilder();
			Document parser = doc.parse(link);
			NodeList list = parser.getElementsByTagName("item");
			List<testSet> testList = new ArrayList<>();
			
			for(int i=0; i<list.getLength();i++) {
				Node tSet = list.item(i);
				NodeList cList = tSet.getChildNodes();
				testSet t = new testSet();
				
				for(int k=0;k<cList.getLength();k++) {
					Node item = cList.item(k);
					String value = item.getNodeName();
					if(value.equals("#text")) continue;
					if(value.equals("defCnt")) t.setDefCnt(item.getTextContent());
					if(value.equals("gubun")) t.setGubun(item.getTextContent());
					if(value.equals("seq")) t.setSeq(item.getTextContent());
					if(value.equals("incDec")) t.setIncDec(item.getTextContent());
				}
				testList.add(t);
			}
			for(testSet t : testList) {
				System.out.println("지역 : " + t.getGubun() + "       감염자 수 : " + t.getIncDec());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		req.setAttribute("aaa", "abc");
		
		return "mpaUsr/home/testPage1";
	}
	

	@RequestMapping("/mpaUsr/home/testPage2")
	public String test22(HttpServletRequest req, HttpServletResponse res) {
		
		
		return "mpaUsr/home/testPage2";
	}
}
