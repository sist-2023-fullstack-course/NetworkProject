package com.sist.client;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.*;
public class ControlPanel extends JPanel {
	NewsPanel np=new NewsPanel();
	ChatPanel cp=new ChatPanel();
	FindPanel fp;
	HomePanel hp;
	DetailPanel dp;
	BoardListPanel bp;
	BoardInsertPanel ip;
	BoardDetailPanel bdp;
	BoardUpdatePanel bup;
	
	RecommendPanel rp=new RecommendPanel();
	String prevCard="home";
	CardLayout card=new CardLayout() {
		@Override
		public void show(Container parent, String name) {
			super.show(parent, name);
			if(name.equals("home"))
				prevCard="home";
			else if(name.equals("find"))
				prevCard="find";
		}
	};
	
	public ControlPanel() {
		fp=new FindPanel(this);
		hp=new HomePanel(this);
		dp=new DetailPanel(this);
		bp=new BoardListPanel(this);
		ip=new BoardInsertPanel(this);
		bdp=new BoardDetailPanel(this);
		bup=new BoardUpdatePanel(this);
		setLayout(card);
		add("home",hp);
		add("news",np);
		add("chat",cp);
		add("recomm",rp);
		add("find",fp);
		add("detail",dp);
		add("board", bp);
		add("insert", ip);
		add("bdp",bdp);
		add("update",bup);
	}
}