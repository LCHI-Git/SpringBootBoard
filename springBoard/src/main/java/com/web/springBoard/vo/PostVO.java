package com.web.springBoard.vo;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component(value = "postVo")
public class PostVO {
	
	int sb_idx;
	String sb_writer;
	String sb_title;
	String sb_content;
	String sb_writeDate;
	int sb_hit;
	String sb_password;
	int sb_commentCount;
	
	public int getSb_commentCount() {
		return sb_commentCount;
	}

	public void setSb_commentCount(int sb_commentCount) {
		this.sb_commentCount = sb_commentCount;
	}

	public PostVO() {
		System.out.println("postVo create");
	}
	
	public int getSb_idx() {
		return sb_idx;
	}
	public void setSb_idx(int sb_idx) {
		this.sb_idx = sb_idx;
	}
	public String getSb_writer() {
		return sb_writer;
	}
	public void setSb_writer(String sb_writer) {
		this.sb_writer = sb_writer;
	}
	public String getSb_title() {
		return sb_title;
	}
	public void setSb_title(String sb_title) {
		this.sb_title = sb_title;
	}
	public String getSb_content() {
		return sb_content;
	}
	public void setSb_content(String sb_content) {
		this.sb_content = sb_content;
	}
	public String getSb_writeDate() {
		return sb_writeDate;
	}
	public void setSb_writeDate(String sb_writeDate) {
		this.sb_writeDate = sb_writeDate;
	}
	public int getSb_hit() {
		return sb_hit;
	}
	public void setSb_hit(int sb_hit) {
		this.sb_hit = sb_hit;
	}
	public String getSb_password() {
		return sb_password;
	}
	public void setSb_password(String sb_password) {
		this.sb_password = sb_password;
	}
	@Override
	public String toString() {
		return "PostVO [idx=" + sb_idx + ", sb_writer=" + sb_writer + ", sb_title=" + sb_title + ", sb_content="
				+ sb_content + ", sb_writeDate=" + sb_writeDate + ", sb_hit=" + sb_hit + ", sb_password=" + sb_password
				+ "]";
	} 
	
	
}



