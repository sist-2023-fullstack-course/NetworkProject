package com.sist.inter;
import com.sist.data.*;
import java.util.*;
public interface BoardInterface {
	// 1. 목록 출력
	public List<BoardVO> boardListData(int page);
	
	// 2. 상세보기
	public BoardVO boardDetailData(int no);
	
	// 3. 글쓰기 
	public void boardInsert(BoardVO vo);
	
	// 4. 수정하기
	public String boardUpdateData(int no);
	
	// 5. 실제 수정
	public String boardUpdate();
	
	// 6. 삭제
	public void boardDelete(int no);
	
	// 7. 자동 증가번호
	public int noIncrement();
}
