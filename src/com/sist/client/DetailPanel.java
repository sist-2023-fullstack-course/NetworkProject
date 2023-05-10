package com.sist.client;
import java.util.*;
import java.awt.*;
import javax.swing.*;

import com.sist.common.ImageChange;
import com.sist.data.InflearnSystem;
import com.sist.data.LectureVO;
import com.sist.common.*;

import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
public class DetailPanel extends JPanel implements ActionListener{

	JLabel imgLa;
    JLabel la1,la2,la3,la4,la5;
    JLabel titleLa,instructorLa,priceLa,levelLa,starLa;
    JLabel linkLa;
    JTextPane contentLa;
    JButton b1,b2;
    ControlPanel cp;
    
    public void printDetails(LectureVO vo) {
		Image img = ImageChange.getImage(InflearnSystem.imgList.get(vo.getId()-1), 530, 350);
		imgLa.setIcon(new ImageIcon(img));
		titleLa.setText(vo.getTitle());
		instructorLa.setText(vo.getInstructor());
		priceLa.setText(vo.getPrice());
		contentLa.setText(vo.getContent());
		levelLa.setText(vo.getLevel());
		starLa.setText(Double.toString(vo.getStar()));
		linkLa.setText(vo.getLink());
		cp.card.show(cp, "detail");
    }
    
    public DetailPanel(ControlPanel cp)
    {
    	this.cp=cp;
    	// 초기화 , 시작과 동시에 구현 
    	// 웹 => 데이터베이스 설정 , 자동로그인 ...
    	imgLa=new JLabel();
    	la1=new JLabel("강의명");
    	la2=new JLabel("강사명");
    	la3=new JLabel("가격");
    	la4=new JLabel("레벨");
    	la5=new JLabel("별점");
    	
    	b1=new JButton("강의사이트");
    	b2=new JButton("목록");
    	
    	titleLa=new JLabel();
    	instructorLa=new JLabel();
    	priceLa=new JLabel();
    	contentLa=new JTextPane();
    	levelLa=new JLabel();
    	starLa=new JLabel();
    	
    	linkLa=new JLabel();

    	// 배치 
    	setLayout(null);

    	imgLa.setBounds(100, 15, 530, 350);
    	add(imgLa);
    	
    	JPanel keyArea = new JPanel();
    	keyArea.setLayout(new GridLayout(5, 1));
    	keyArea.setBounds(100, 375, 80, 200);
    	keyArea.add(la1); keyArea.add(la2);
    	keyArea.add(la3); keyArea.add(la4);
    	keyArea.add(la5); 
    	add(keyArea);
    	
    	JPanel valueArea = new JPanel();
    	valueArea.setLayout(new GridLayout(5, 1));
    	valueArea.setBounds(185, 375, 450, 200);
    	valueArea.add(titleLa); valueArea.add(instructorLa);
    	valueArea.add(priceLa); valueArea.add(levelLa); 
    	valueArea.add(starLa);
    	add(valueArea);
    	
    	contentLa.setBounds(100, 580, 530, 80);
    	contentLa.setEditable(false);
    	add(contentLa);
    	
    	JPanel p=new JPanel();
    	p.add(b1);
    	p.add(b2);
    	p.setBounds(100, 670, 530, 40);
    	add(p);
    	
    	// actionlistener
    	b1.addActionListener(this);
    	b2.addActionListener(this);
    }
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1)
		{
			try
			{
				Runtime.getRuntime().exec(env.chromePath + " " + linkLa.getText());	
			}catch(Exception ex){}
		}
		else if(e.getSource()==b2)
		{
			cp.card.show(cp, "home");
		}
		
	}

}
