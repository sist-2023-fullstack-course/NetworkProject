package com.sist.data;

import java.io.Serializable;

public class LectureVO implements Serializable {
	private int id;
	private int cno;
	private String title;
	private String content;
	private String level;
	private String price; 
	private double star;
	private String instructor;
	private String poster;
	private String link;
	private int reviewcnt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public double getStar() {
		return star;
	}

	public void setStar(double star) {
		this.star = star;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getReviewcnt() {
		return reviewcnt;
	}

	public void setReviewcnt(int reviewcnt) {
		this.reviewcnt = reviewcnt;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append("|");
		sb.append(cno);
		sb.append("|");
		sb.append(title);
		sb.append("|");
		sb.append(level);
		sb.append("|");
		sb.append(price);
		sb.append("|");
		sb.append(star);
		sb.append("|");
		sb.append(reviewcnt);
		sb.append("|");
		sb.append(instructor);
		sb.append("\n");
		sb.append("이미지주소:");
		sb.append(poster);
		sb.append("\n");
		sb.append(content);
		sb.append("\n====================================================");
		return sb.toString();
	}
}