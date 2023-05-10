package com.sist.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.sound.sampled.AudioFormat.Encoding;
import javax.swing.ImageIcon;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class DataCollectionManager {
	static String inflearnURL = "https://www.inflearn.com/";
	static String[] categoryURL = { 
			"courses/it-programming/web-dev",
			"courses/it-programming/back-end",
			"courses/it-programming/full-stack",
			"courses/it-programming/mobile-app",
			"courses/it-programming/programming-lang?order=seq",
			"courses/it-programming/algorithm?order=seq"
	};
	static String filePath = "c:\\java_datas\\datas.ser";

	public static List<LectureVO> collectData() throws IOException {
		List<LectureVO> list = new ArrayList<LectureVO>();
		
		int id = 1;
		for (int i = 0; i < categoryURL.length; i++) {
			Document doc = Jsoup.connect(inflearnURL + categoryURL[i]).get();
			Elements title = doc.select("div.card-content div.course_title");
			Elements content = doc.select("div.course_card_back p.course_description");
			Elements level = doc.select("div.back_course_metas div.course_level span");
			Elements price = doc.select("div.price");
			Elements instructor = doc.select("div.instructor");
			Elements poster = doc.select("img.swiper-lazy");
			Elements courseURL = doc.select("a.course_card_front");
			for (int j = 0; j < title.size(); j++) {
				LectureVO vo = new LectureVO();
				vo.setId(id++);
				vo.setCno(i + 1);
				vo.setTitle(title.get(j).text());
				vo.setContent(content.get(j).text());
				vo.setLevel(level.get(j).text());
				String p = price.get(j).text();
				String pArr[] = p.split(" ");
				vo.setPrice(pArr[pArr.length - 1]);
				vo.setInstructor(instructor.get(j).text());
				vo.setPoster(poster.get(j).attr("src"));
				String url = courseURL.get(j).attr("abs:href");
				Document inner = Jsoup.connect(url).get();
				Element star = inner.selectFirst("div.dashboard-star__num");
				if(Objects.isNull(star))
					vo.setStar(0.0);
				else
					vo.setStar(Double.parseDouble(star.text()));
				list.add(vo);
			}
		}

		return list;
	}
	
	public static void writeData(List<LectureVO> list) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
		oos.writeObject(list);
	}
	
	public static List<LectureVO> readData() throws IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
		return (List<LectureVO>)ois.readObject();
	}

	public static void main(String[] args) {
		try {
			writeData(collectData());
			List<LectureVO> data = readData();
			
			List<ImageIcon> imgList = new ArrayList<ImageIcon>();
			for(LectureVO vo : data) {
				System.out.println(vo);
				imgList.add(new ImageIcon(new URL(encodeURL(vo.getPoster()))));
			}
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("c:\\java_datas\\imgDatas.ser"));
			oos.writeObject(imgList);
			System.out.println("저장완료~~");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static String encodeURL(String url) {
		try {
		    // URL 디코딩을 수행하여 인코딩된 부분을 일반 문자로 변환
		    String decodedUrl = URLDecoder.decode(url, "UTF-8");
		    String[] parts = decodedUrl.split("/");
		    for (int i = 0; i < parts.length; i++) {
		        if (i == 0) {
		            // 스킴(http://, https://)부분은 그대로 두기
		            continue;
		        }
		        // 이미 인코딩된 부분은 그대로 두기
		        if (parts[i].matches("%[0-9a-fA-F]{2}")) {
		            continue;
		        }
		        try {
		            // UTF-8로 인코딩한 결과를 다시 URL 인코딩하기
		            String encodedPart = URLEncoder.encode(
		                new String(parts[i].getBytes("UTF-8")), "UTF-8"
		            ).replace("+", "%20").replace("%21", "!").replace("%27", "'")
		             .replace("%28", "(").replace("%29", ")").replace("%7E", "~");
		            parts[i] = encodedPart;
		        } catch (UnsupportedClassVersionError e) {
		            // 한글이 아닌 경우에는 그대로 두기
		            // 예외처리 코드
		        }
		    }
		    url = String.join("/", parts);
		} catch (UnsupportedEncodingException e) {
		    // 디코딩 실패시 예외처리 코드
		}
		return url;
	}
}
