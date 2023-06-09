package com.sist.client;

import java.awt.*;
import javax.swing.*;

import com.sist.data.NaverNewsManager;
import com.sist.data.NewsVO;

import java.awt.event.*;
import java.util.List;
import java.util.*;
public class NewsPanel extends JPanel implements ActionListener{
   NewsCard[] cards=new NewsCard[10];
   JTextField tf;
   JButton b;
   JPanel pan;
   JScrollPane pane;
   public NewsPanel()
   {
	   // 초기화 
	   tf=new JTextField();
	   b=new JButton("검색");
	   for(int i=0;i<cards.length;i++)
	   {
		   cards[i]=new NewsCard();
	   }
	   pan=new JPanel();
	   pan.setLayout(new GridLayout(10,1,5,5));
	   
	   // 배치
	   setLayout(null);
	   tf.setBounds(10, 15, 250, 30);
	   b.setBounds(265, 15, 100, 30);
	   pan.setPreferredSize(new Dimension(690,1100));
	   pane=new JScrollPane(pan);
	   pane.setPreferredSize(new Dimension(700,650));
	   pane.setBounds(10, 55, 720, 650);
	   
	   add(tf);
	   add(b);
	   add(pane);
	   
	   // 등록 
	   tf.addActionListener(this);
	   b.addActionListener(this);
	   newsPrint("인프런");
   }
    public void newsPrint(String fd)
    {
    	List<NewsVO> list=NaverNewsManager.newsSearchData(fd);
		pan.setLayout(new GridLayout(10,1,5,5));
		int i=0;
		for(NewsVO vo:list)
		{
			cards[i].titleLa.setText(vo.getTitle());
			cards[i].contentTa.setText(vo.getContent());
			cards[i].dateLa.setText(vo.getDate());
			pan.add(cards[i]);
			i++;
		}
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==tf || e.getSource()==b)
		{
			String fd=tf.getText();
			// 웹 => 유효성 검사 
			if(fd.length()<1)
			{
				JOptionPane.showMessageDialog(this, "검색어 입력");
				tf.requestFocus();
				return;
			}
			newsPrint(fd);
		}
	}
}