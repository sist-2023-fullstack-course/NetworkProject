package com.sist.data;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class NaverNewsManager {
	private static String removeHtmlTag(String s) {
		if(s == null) {
	        return null;
	    }
	    s = s.replaceAll("&gt;", ">");
	    s = s.replaceAll("&lt;", "<");
	    s = s.replaceAll("&quot;", "");
	    s = s.replaceAll("&apos;", "'");
	    s = s.replaceAll("&nbsp;", " ");
	    s = s.replaceAll("&amp;", "&");
	    s = s.replaceAll("<br>", "\n");
	    s = s.replaceAll("<b>", "");
	    s = s.replaceAll("</b>", "");
	    return s;
	}
    public static List<NewsVO> newsSearchData(String fd) {
    	List<NewsVO> list=new ArrayList<NewsVO>();
        String clientId = "ATsye14KFNTQUc8OcBlA"; //애플리케이션 클라이언트 아이디
        String clientSecret = "ACZc2lTqWI"; //애플리케이션 클라이언트 시크릿


        String text = null;
        try {
            text = URLEncoder.encode(fd, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }


        String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + text;    // JSON 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // XML 결과


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);

        
        try
        {
        	JSONParser jp=new JSONParser();
        	JSONObject root=(JSONObject)jp.parse(responseBody);
        	JSONArray arr=(JSONArray)root.get("items");
        
        	for(int i=0;i<arr.size();i++)
        	{
        		NewsVO vo=new NewsVO();
        		JSONObject obj=(JSONObject)arr.get(i);
        		vo.setTitle(removeHtmlTag(obj.get("title").toString()));
        		vo.setContent(removeHtmlTag(obj.get("description").toString()));
        		vo.setLink(removeHtmlTag(obj.get("link").toString()));
        		String date=removeHtmlTag(obj.get("pubDate").toString());
        		date=new SimpleDateFormat("yyyy-MM-dd").format(new Date(date));
        		vo.setDate(date);
        		list.add(vo);
        	}
        }catch(Exception ex){}
        return list;
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
}