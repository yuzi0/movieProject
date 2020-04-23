package com.exam.model;

public class MovieSortTO {

	private int mnum;
	private String msubject;
	private String score;
	private String img;
	private String genre;
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getMnum() {
		return mnum;
	}
	public void setMnum(int mnum) {
		this.mnum = mnum;
	}
	public String getMsubject() {
		return msubject;
	}
	public void setMsubject(String msubject) {
		this.msubject = msubject;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
}
