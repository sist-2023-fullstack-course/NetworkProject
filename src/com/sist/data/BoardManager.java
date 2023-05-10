package com.sist.data;
import java.util.*;

import com.sist.inter.BoardInterface;

import java.io.*;
public class BoardManager implements BoardInterface{
	// 게시물 전체를 가지고 있는다 => List => 접속한 모든 사람이 공유
	private static List<BoardVO> list = new ArrayList<BoardVO>();
	
	static {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("c:\\java_datas\\board.txt"));
			list = (List<BoardVO>)ois.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ois.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<BoardVO> boardListData(int page) {
		return list.subList((page-1)*10, Math.min(page*10, list.size()));
	}
	
	public int boardTotalPage() {
		return (int)(Math.ceil(list.size()/10.0));
	}

	@Override
	public BoardVO boardDetailData(int no) {
		BoardVO vo = new BoardVO();
		for(BoardVO v : list) {
			if(no==v.getNo())
			{
				vo=v;
			}
		}
		return vo;
	}

	@Override
	public void boardInsert(BoardVO vo) {
		vo.setNo(noIncrement());
		list.add(vo);
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("c:\\java_datas\\board.txt"))){
			oos.writeObject(list);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String boardUpdateData(int no) {
		return null;
	}

	@Override
	public String boardUpdate() {
		return null;
	}

	@Override
	public String boardDelete(int no, String pwd) {
		return null;
	}

	@Override
	public int noIncrement() {
		int max = 0;
		for(BoardVO vo : list) {
			if(vo.getNo()>max)
				max=vo.getNo();
		}
		return max+1;
	}
}
