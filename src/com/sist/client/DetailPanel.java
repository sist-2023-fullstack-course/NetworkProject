package com.sist.client;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class DetailPanel extends JPanel implements ActionListener{

	JLabel imgLa;
    JLabel la1,la2,la3,la4,la5;
    JButton b1,b2;
    JLabel titleLa,instructorLa,priceLa,contentLa,levelLa,keyLa;
    ControlPanel cp;
    
    public DetailPanel(ControlPanel cp)
    {
    	this.cp=cp;
    	// 초기화 , 시작과 동시에 구현 
    	// 웹 => 데이터베이스 설정 , 자동로그인 ...
    	imgLa=new JLabel();
    	la1=new JLabel("강의명");
    	la2=new JLabel("강사명");
    	la3=new JLabel("가격");
    	la4=new JLabel("내용");
    	la5=new JLabel("레벨");
    	
    	b1=new JButton("동영상");
    	b2=new JButton("목록");
    	
    	titleLa=new JLabel("");
    	instructorLa=new JLabel("");
    	priceLa=new JLabel("");
    	contentLa=new JLabel("");
    	levelLa=new JLabel("");
    	keyLa=new JLabel(""); //영상 갖고오는 레이블 visible false처리..
    	// 배치 
    	setLayout(null);
    	imgLa.setBounds(100, 15, 530, 350);
    	
    	la1.setBounds(100, 375, 60, 40);
    	titleLa.setBounds(165, 375, 300, 40);
    	
    	la2.setBounds(100, 425, 60, 40);
    	instructorLa.setBounds(165, 425, 300, 40);
    	
    	la3.setBounds(100, 475, 60, 40);
    	priceLa.setBounds(165, 475, 300, 40);
    	
    	la4.setBounds(100, 525, 60, 40);
    	contentLa.setBounds(165, 525, 300, 40);
    	
    	la5.setBounds(100, 575, 60, 40);
    	levelLa.setBounds(165, 575, 300, 40);
    	
    	keyLa.setBounds(100, 625, 300, 40);
    	keyLa.setVisible(false);
    	
    	JPanel p=new JPanel();
    	p.add(b1);
    	p.add(b2);
    	p.setBounds(100,670 , 365, 40);
    	
    	// 윈도우 추가
    	add(imgLa);
    	add(keyLa);
    	add(la1);add(titleLa);
    	add(la2);add(instructorLa);
    	add(la3);add(priceLa);
    	add(la4);add(contentLa);
    	add(la5);add(levelLa);
    	add(p);
    	
    	b1.addActionListener(this);
    	b2.addActionListener(this);
    }
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==b1)
		{
			try
			{
				Runtime.getRuntime().exec("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe "
						            +"http://youtube.com/embed/"
						            +keyLa.getText());
			}catch(Exception ex){}
		}
		else if(e.getSource()==b2)
		{
			cp.card.show(cp, "home");
		}
		
	}

}
