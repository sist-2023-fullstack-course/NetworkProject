package com.sist.client;
import java.util.*;
import com.sist.data.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BoardUpdatePanel extends JPanel implements ActionListener{
	ControlPanel cp;
	JLabel titleLa;
	JLabel la1, la2, la3, la4;
	JTextField tf1, tf2;
	JPasswordField pf;
	JTextArea ta;
	JButton b1, b2;
	BoardVO vo = null;
	BoardManager bm = new BoardManager();
	
	public BoardUpdatePanel() {
		titleLa = new JLabel("자유게시판");
		titleLa.setFont(new Font("맑은 고딕", Font.BOLD, 45));
		titleLa.setHorizontalAlignment(JLabel.CENTER);
		
		la1 = new JLabel("이름");
		la2 = new JLabel("제목");
		la3 = new JLabel("내용");
		la4 = new JLabel("비밀번호");
		
		tf1 = new JTextField();
		tf2 = new JTextField();
		ta = new JTextArea();
		JScrollPane js = new JScrollPane(ta);
		pf = new JPasswordField();
		
		b1 = new JButton("수정하기");
		b2 = new JButton("취소");
		
		// 배치
		setLayout(null);
		titleLa.setBounds(10, 15, 720, 60);
		add(titleLa);
		
		la1.setBounds(10, 85, 80, 30);
		tf1.setBounds(95, 85, 150, 30);

		la2.setBounds(10, 120, 80, 30);
		tf2.setBounds(95, 120, 150, 30);
		
		la3.setBounds(10, 155, 80, 30);
		js.setBounds(95, 155, 450, 350);
		
		la4.setBounds(10, 510, 80, 30);
		pf.setBounds(95, 510, 150, 30);
		
		JPanel p = new JPanel();
		p.add(b1);
		p.add(b2);
		p.setBounds(10, 545, 535, 35);
		
		add(la1); add(tf1);
		add(la2); add(tf2);
		add(la3); add(js);
		add(la4); add(pf);
		add(p);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
	}
	public BoardUpdatePanel(ControlPanel cp) {
		this();
		this.cp = cp;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			String name = tf1.getText();
			if(name.length()<1) {
				tf1.requestFocus();
				return;
			}
			String subject = tf2.getText();
			if(subject.length()<1) {
				tf2.requestFocus();
				return;
			}
			String content = ta.getText();
			if(content.length()<1) {
				ta.requestFocus();
				return;
			}
			String pwd = String.valueOf(pf.getPassword());
			if(pwd.length()<1) {
				pf.requestFocus();
				return;
			}
			
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			vo.setRegdate(new Date());
			
			cp.bp.boardPrint();
			cp.card.show(cp, "board");
		}
		else if(e.getSource()==b2) {
			cp.card.show(cp, "board");
		}
	}
}
