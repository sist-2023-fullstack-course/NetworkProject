package com.sist.client;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.net.*;

import com.sist.common.ImageChange;
import com.sist.data.InflearnSystem;
import com.sist.data.LectureVO;
public class PosterCard extends JPanel{
	LectureVO vo;
	JLabel poLa; // 이미지 영역
	JLabel tLa; // 제목 영역
	
	public PosterCard() {
		setLayout(null);
		
		poLa = new JLabel();
		poLa.setBounds(5, 5, 130, 130);
		add(poLa);

		tLa = new JLabel();
		tLa.setBounds(5, 140, 150, 30);
		add(tLa);
	}
	public PosterCard(LectureVO vo) {
		this();
		this.vo = vo;
		
		try {
			Image img = ImageChange.getImage(InflearnSystem.imgList.get(vo.getId()-1), 130, 130);
			poLa.setIcon(new ImageIcon(img));
			tLa.setText(vo.getTitle());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
