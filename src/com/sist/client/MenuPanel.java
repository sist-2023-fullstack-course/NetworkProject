package com.sist.client;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{
	JButton b1,b2,b3,b4,b5,b6;
	public MenuPanel() {
		setBounds(10, 200, 200, 300);
		b1=new JButton("홈");
		b2=new JButton("강의검색");
		b3=new JButton("채팅");
		b4=new JButton("뉴스검색");
		b5=new JButton("커뮤니티");
		b6=new JButton("나가기");
		setLayout(new GridLayout(6,1,10,10));
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		add(b6);
	}
}
