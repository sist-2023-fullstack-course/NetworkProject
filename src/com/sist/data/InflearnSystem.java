package com.sist.data;

import java.util.ArrayList;
import java.util.List;

import com.sist.common.env;

import java.io.*;

public class InflearnSystem {
	public static List<LectureVO> list = new ArrayList<LectureVO>();
	static {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(env.dataUrl);
			ois = new ObjectInputStream(fis);
			
			list = (List<LectureVO>)ois.readObject();
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
	public LectureVO lectureDetailData(String title)
	{
		LectureVO vo=new LectureVO();
		for(LectureVO gvo:list)
		{
			if(gvo.getTitle().equals(title))
			{
				vo=gvo;
				break;
			}
		}
		return vo;
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
