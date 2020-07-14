package com.web.springBoard.vo;

import org.springframework.stereotype.Component;

@Component(value="commentVo")
public class CommentVO {

	private int comment_idx;
	private int comment_sb_idx;
	private String comment_writer;
	private String comment_password;
	public String getComment_password() {
		return comment_password;
	}
	public void setComment_password(String comment_password) {
		this.comment_password = comment_password;
	}

	private String comment_content;
	private String comment_writeDate;
	
	public int getComment_idx() {
		return comment_idx;
	}
	public void setComment_idx(int comment_idx) {
		this.comment_idx = comment_idx;
	}
	public int getSb_idx() {
		return comment_sb_idx;
	}
	public void setSb_idx(int sb_idx) {
		this.comment_sb_idx = sb_idx;
	}
	public String getComment_writer() {
		return comment_writer;
	}
	public void setComment_writer(String comment_writer) {
		this.comment_writer = comment_writer;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String getComment_writeDate() {
		return comment_writeDate;
	}
	public void setComment_writeDate(String comment_writeDate) {
		this.comment_writeDate = comment_writeDate;
	}

	@Override
	public String toString() {
		return "CommentVO [comment_idx=" + comment_idx + ", comment_sb_idx=" + comment_sb_idx + ", comment_writer="
				+ comment_writer + ", comment_password=" + comment_password + ", comment_content=" + comment_content
				+ ", comment_writeDate=" + comment_writeDate + "]";
	}
	
}
