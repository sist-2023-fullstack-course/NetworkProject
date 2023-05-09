package com.sist.data;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.sist.common.ImageChange;
import com.sist.common.env;

import java.awt.Image;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

public class InflearnSystem {
	public static List<LectureVO> list = new ArrayList<LectureVO>();
	public static List<ImageIcon> imgList = new ArrayList<ImageIcon>();
	static {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(env.dataUrl);
			ois = new ObjectInputStream(fis);
			
			list = (List<LectureVO>)ois.readObject();
			
			for(LectureVO vo : list) {
				imgList.add(new ImageIcon(new URL(vo.getPoster())));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ois.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static List<LectureVO> lectureCategoryData(int cno) {
		List<LectureVO> ret = new ArrayList<LectureVO>();
		for(LectureVO vo : list) {
			if(vo.getCno()==cno) {
				ret.add(vo);
			}
		}
		return ret;
	}
	
	public static List<LectureVO> FindLecture(String title){
		List<LectureVO> ret = new ArrayList<LectureVO>();
		for(LectureVO vo : list) {
			if(vo.getTitle().contains(title)) {
				ret.add(vo);
			}
		}
		return ret;
	}
}
