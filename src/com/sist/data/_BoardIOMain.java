package com.sist.data;
import java.util.*;
import java.io.*;
public class _BoardIOMain {
	public static void main(String[] args) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("c:\\java_datas\\board.txt"));
			List<BoardVO> list = new ArrayList<BoardVO>();
			
			BoardVO vo = new BoardVO();
			vo.setNo(1);
			vo.setName("홍길동");
			vo.setSubject("파일 입출력을 이용한 게시판 만들기");
			vo.setContent("파일 입출력을 이용한 게시판 만들기");
			vo.setRegdate(new Date());
			vo.setPwd("1234");
			vo.setHit(0);
			list.add(vo);
			
			oos.writeObject(list);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				oos.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
