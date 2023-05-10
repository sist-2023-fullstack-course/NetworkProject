package com.sist.client;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.*;
public class ControlPanel extends JPanel {
	NewsPanel np=new NewsPanel();
	ChatPanel cp=new ChatPanel();
	FindPanel fp=new FindPanel();
	HomePanel hp;
	DetailPanel dp;
	BoardListPanel bp;
	BoardInsertPanel ip;
	BoardDetailPanel bdp;
	BoardUpdatePanel bup;
	
	RecommendPanel rp=new RecommendPanel();
	CardLayout card=new CardLayout();
	
	public ControlPanel() {
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
		add("find",fp);
		add("recomm",rp);
		add("detail",dp);
		add("board", bp);
		add("insert", ip);
		add("bdp",bdp);
		add("update",bup);
	}
}