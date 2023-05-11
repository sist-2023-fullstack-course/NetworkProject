package com.sist.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.sist.common.ImageChange;
import com.sist.data.InflearnSystem;
import com.sist.data.LectureVO;

public class TopPanel extends JPanel{
	JLabel title;
	JTable table;
	DefaultTableModel model;
	ControlPanel cp;
	List<LectureVO> list;
	
	public TopPanel(ControlPanel cp) {
		this();
		this.cp = cp;
	}
	public TopPanel() {
		setLayout(null);
		
		title = new JLabel();
		title.setText("리뷰왕 TOP10");
		title.setBounds(5, 5, 340, 90);
		title.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		add(title);
		
		String[] col = {"", "제목", "평점(리뷰)"};
		Object[][] row = new Object[0][3];
		
		model = new DefaultTableModel(row, col) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return getValueAt(0, columnIndex).getClass();
			}
		};
		
		table = new JTable(model);
		table.getColumn("").setPreferredWidth(50);
		table.getColumn("제목").setPreferredWidth(220);
		table.getColumn("평점(리뷰)").setPreferredWidth(70);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setRowHeight(58);
		
		JScrollPane js = new JScrollPane(table);
		js.setBounds(5, 100, 340, 600);
		add(js);
		
		list = InflearnSystem.getMostReviewedData();
		
		try {
			for(LectureVO vo : list) {
				Image img = ImageChange.getImage(InflearnSystem.imgList.get(vo.getId()-1), 40, 40);
				Object[] data= {
						new ImageIcon(img),
						vo.getTitle(),
						vo.getStar()+"("+vo.getReviewcnt()+")"
				};
				model.addRow(data);
			}
		} catch (Exception e) {
		}
//		table.addMouseListener(this);
	}
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		if(e.getSource()==table && e.getClickCount()==2) {
//			int row = table.getSelectedColumn();
//			cp.dp.printDetails(list.get(row));
//		}
//	}
//	@Override
//	public void mousePressed(MouseEvent e) {
//		
//	}
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		
//	}
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		
//	}
//	@Override
//	public void mouseExited(MouseEvent e) {
//		
//	}
}

