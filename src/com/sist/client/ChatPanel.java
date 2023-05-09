package com.sist.client;

import java.awt.Color;

import javax.swing.*;

import javax.swing.table.*;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
// 750 730
public class ChatPanel extends JPanel {
	JTextPane pane; // 편집 가능
	JTextField tf; // 입력창
	JButton b1,b2; // 쪽지보내기, 정보보기
	JTable table; // 화면 UI (접속한 사람)
	DefaultTableModel model; // 데이터 관리 (접속한 사람)
	JComboBox<String> box;
	/*
	 * 화면 관리 / 데이터 관리를 따로하는 프로그램
	 * ------   --------
	 *  View  +   Model  == 연결 ==>  Controller
	 */
	public ChatPanel() {
		// 초기화
		pane=new JTextPane();
		pane.setEditable(false);
		JScrollPane js1=new JScrollPane(pane); // 화면 스크롤
		tf=new JTextField();
		box=new JComboBox<String>();
		// red => 알림
		box.addItem("black");
		box.addItem("blue");
		box.addItem("yellow");
		box.addItem("green");
		box.addItem("pink");
		box.addItem("orange");
		box.addItem("cyan");
		
		// 테이블
		String[] col= {"아이디","이름","성별"};
		String[][] row=new String[0][3];
		model=new DefaultTableModel(row,col)
		{
			@Override
			public boolean isCellEditable(int row, int column) {
			return false;
			}
			
		};
		table=new JTable(model);
		JScrollPane js2=new JScrollPane(table);
		b1=new JButton("쪽지보내기");
		b2=new JButton("정보보기");
		JPanel p=new JPanel();
		p.add(b1);
		p.add(b2);
		// 배치
		setLayout(null);
		js1.setBounds(10, 15, 500, 600);
		tf.setBounds(10, 620, 380, 30);
		box.setBounds(395, 620, 115, 30);
		js2.setBounds(520, 15, 230, 250);
		p.setBounds(520, 270, 230, 35);
		
		add(js1);
		add(tf); add(box);
		add(js2);
		add(p);
		b1.setEnabled(false);
		b2.setEnabled(false);
//		String[] data= {"hong","홍길동","남자"};
//		model.addRow(data); // 접속자 명단 출력
		
	}
	public void initStyle() {
		Style blue=pane.addStyle("blue", null);
		StyleConstants.setForeground(blue, Color.blue);
		
		Style green=pane.addStyle("green", null);
		StyleConstants.setForeground(green, Color.green);
		
		Style yellow=pane.addStyle("yellow", null);
		StyleConstants.setForeground(yellow, Color.yellow);
		
		Style pink=pane.addStyle("pink", null);
		StyleConstants.setForeground(pink, Color.pink);
		
		Style orange=pane.addStyle("orange", null);
		StyleConstants.setForeground(orange, Color.orange);
		
		Style cyan=pane.addStyle("cyan", null);
		StyleConstants.setForeground(cyan, Color.cyan);
	}
	// 이벤트
	public void append(String msg, String color) {
		try {
			Document doc= pane.getDocument();
			doc.insertString(doc.getLength(), msg+"\n", pane.getStyle(color));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}