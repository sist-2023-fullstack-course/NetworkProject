package com.sist.client;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sist.common.ImageChange;
import com.sist.data.InflearnSystem;
import com.sist.data.LectureVO;

public class HomePanel extends JPanel implements MouseListener{
	PosterCard[] pcs=new PosterCard[20];
	JPanel pan;
	JPanel pageCon;
	JButton b1, b2;
	JLabel pageLa;
	ControlPanel cp;
	InflearnSystem is=new InflearnSystem();
	public HomePanel() {}
	public HomePanel(ControlPanel cp) {
		
		this.cp=cp;
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
		int j=0;
		for(int i=(page-1)*20;i<page*20;i++) {
			if(i<InflearnSystem.list.size()) {
				pcs[j] = new PosterCard(InflearnSystem.list.get(i));
				pcs[j].addMouseListener(this);
				pan.add(pcs[j++]);
			}
			else {
				pan.add(new PosterCard());
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i=0;i<pcs.length;i++)
		{
			if(e.getSource()==pcs[i])
			{
				cp.dp.printDetails(pcs[i].vo);
				break;
			}
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
