package com.sist.client;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sist.data.InflearnSystem;

public class HomePanel extends JPanel{
	JPanel pan;
	JPanel pageCon;
	JButton b1, b2;
	JLabel pageLa;
	public HomePanel() {
		setLayout(null);
		b1=new JButton("이전");
		b2=new JButton("다음");
		pageLa = new JLabel();
		
		pan = new JPanel();
		pan.setLayout(new GridLayout(4, 5, 5, 5));
		pan.setBounds(10, 15, 730, 700);
		add(pan);

		pageCon = new JPanel();
		pageCon.add(b1);
		pageCon.add(pageLa);
		pageCon.add(b2);
		pageCon.setBounds(10, 720, 730, 35);
		add(pageCon);
		
	}
	public void cardPrint(int page) {
		pan.removeAll(); // 데이터 제거
		for(int i=(page-1)*20;i<Math.min(page*20, InflearnSystem.list.size());i++) {
			pan.add(new PosterCard(InflearnSystem.list.get(i)));
		}
	}
}
