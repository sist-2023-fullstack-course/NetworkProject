package com.sist.client;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.net.*;

import com.sist.common.ImageChange;
import com.sist.data.LectureVO;
public class PosterCard extends JPanel{
	JLabel poLa = new JLabel(); // 이미지 출력
	JLabel tLa = new JLabel(); // 제목 출력
	
	public PosterCard() {}
	public PosterCard(LectureVO vo) {
		setLayout(null);
		setBorder(new LineBorder(Color.black));
		
		poLa.setBounds(5, 5, 130, 130);
		tLa.setBounds(5, 140, 150, 30);
		
		add(poLa);
		add(tLa);
		
		try {
			URL url = new URL(vo.getPoster());
			Image img = ImageChange.getImage(new ImageIcon(url), 130, 130);
			poLa.setIcon(new ImageIcon(img));
			tLa.setText(vo.getTitle());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
