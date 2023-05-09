package com.sist.client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.net.*;
import java.io.*;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.sist.common.*;
import com.sist.data.InflearnSystem;
import com.sist.data.LectureVO;
public class NetworkMain extends JFrame implements ActionListener, Runnable{
		MenuPanel mp;
		ControlPanel cp;
		TopPanel tp;
		JButton b1,b2,b3,b4,b5;
		JLabel logo;
		Login login;
		int curpage = 1;
		final int totalpage = InflearnSystem.list.size()/20;
		
		//네크워크 관련 클래스
		Socket s;
		BufferedReader in;
		OutputStream out;
		
		
		public NetworkMain() {
			logo=new JLabel();
			Image img=ImageChange.getImage(new ImageIcon(env.logoUrl), 200, 60);
			logo.setIcon(new ImageIcon(img));
			
			mp=new MenuPanel();
			cp=new ControlPanel();
			tp=new TopPanel();
			
			setLayout(null); // Layout없이 직접 배치
			logo.setBounds(10, 15, 200, 130);
			mp.setBounds(10, 150, 200, 300);
			cp.setBounds(220, 15, 750, 780);
			tp.setBounds(980, 15, 200, 780);
			
			// 메뉴 배치
			b1=new JButton("홈");
			b2=new JButton("강의검색");
			b3=new JButton("채팅");
			b4=new JButton("뉴스검색");
			b5=new JButton("강의추천");
			mp.setLayout(new GridLayout(5,1,10,10));
			mp.add(b1);
			mp.add(b2);
			mp.add(b3);
			mp.add(b4);
			mp.add(b5);
			
			// 추가
			add(mp);
			add(cp);
			add(tp);
			add(logo);
			
			// 윈도우 크기
			setSize(1200, 850);
			
			// 종료
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setTitle("네트워크 뮤직 프로그램");
			
			// 이벤트 등록
			b1.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			b4.addActionListener(this);
			b5.addActionListener(this);
			
			// 로그인
			login = new Login();
			login.b1.addActionListener(this);
			login.b2.addActionListener(this);
			
			// 채팅
			cp.cp.tf.addActionListener(this);
			cp.hp.b1.addActionListener(this);
			cp.hp.b2.addActionListener(this);
		}
	public void LectureDisplay() {
		cp.hp.cardPrint(curpage);
		cp.hp.pageLa.setText(curpage+" page / "+totalpage+" pages");
	}
	// 버튼 처리
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			curpage=1;
			LectureDisplay();
			cp.card.show(cp, "home");
		}
		else if(e.getSource()==b2) {
			cp.card.show(cp, "find");
		}
		else if(e.getSource()==b3) {
			cp.card.show(cp, "chat");
		}
		else if(e.getSource()==b4) {
			cp.card.show(cp, "news");
		}
		else if(e.getSource()==b5) {
			cp.card.show(cp, "recomm");
		}
		else if(e.getSource()==login.b1) {
			String id = login.tf1.getText();
			if(id.length()<1) {
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
				login.tf1.requestFocus();
				return;
			}
			String name = login.tf2.getText();
			if(name.length()<1) {
				JOptionPane.showMessageDialog(this, "이름을 입력하세요");
				login.tf2.requestFocus();
				return;
			}
			String sex = login.rb1.isSelected()?"남자":"여자";
			
			//서버로 전송
			try {
				s = new Socket(env.serverAddress, 10000);
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				out = s.getOutputStream();
				
				out.write((Function.LOGIN+"|"+id+"|"+name+"|"+sex+"\n").getBytes());
			}catch (Exception ex) {
				ex.printStackTrace();
			}
			new Thread(this).start();
		}
		else if(e.getSource()==login.b2) {
			System.exit(0); // 프로그램 종료
		}
		else if(e.getSource()==cp.cp.tf) {
//			cp.cp.initStyle();
			String msg=cp.cp.tf.getText();
			String color=cp.cp.box.getSelectedItem().toString();
			if(msg.length()<1)
				return;
			
			try {
				out.write((Function.CHAT+"|"+msg+"|"+color+"\n").getBytes());
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			
			cp.cp.tf.setText("");
		}
		else if(e.getSource() == cp.hp.b1) { // 이전
			if(curpage>1) {
				curpage--;
				LectureDisplay();
			}
		}
		else if(e.getSource() == cp.hp.b2) { // 다음
			if(curpage<totalpage) {
				curpage++;
				LectureDisplay();
			}
		}
		
	}

	@Override
	public void run() {
		try {
			while(true) {
				String msg = in.readLine();
				StringTokenizer st = new StringTokenizer(msg,"|");
				int protocol = Integer.parseInt(st.nextToken());
				
				switch(protocol) {
				case Function.LOGIN:{
					String[] data = {st.nextToken(), st.nextToken(), st.nextToken()};
					cp.cp.model.addRow(data);
					break;
				}
				case Function.MYLOG:{
					setTitle(st.nextToken());
					login.setVisible(false);
					setVisible(true);
					LectureDisplay();
					break;
				}
				case Function.CHAT:{
					cp.cp.initStyle();
					cp.cp.append(st.nextToken(), st.nextToken());
					break;
				}
				case Function.LOGOUT:{
					String id = st.nextToken();
					String name = st.nextToken();
					String sex = st.nextToken();
					for(int i=0;i<cp.cp.model.getRowCount();i++) {
						if(id.equals(cp.cp.model.getValueAt(i, 0)) &&
								name.equals(cp.cp.model.getValueAt(i, 1)) &&
								sex.equals(cp.cp.model.getValueAt(i, 2)))
							cp.cp.model.removeRow(i);
					}
					break;
				}
				}
						
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try 
		{
			UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
		}catch(Exception ex){}
		new NetworkMain();
	}
	
}