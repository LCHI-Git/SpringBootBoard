package com.web.springBoard.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.web.springBoard.vo.CommentVO;
import com.web.springBoard.vo.PostVO;

public interface BoardService {
	
//	Spring Board Service part
	
	int getTotalCount();
	
	ArrayList<PostVO> getPostArr(Map<String, Integer> seNum);	// 한 페이지 분량 포스트 얻어오기
	
	void insertPost(PostVO postVo);								// 포스트 삽입
	
	String getPassword(String sb_idx);							// 선택 포스트이 비밀번호 얻어오기

	void deletePost(String parameter);							// 선택 포스트 삭제하기

	PostVO getPostByIdx(String sb_idx);							// 해당 인자에 해당하는 포스트 얻어오기

	void updatePost(PostVO postVo);								// 포스트 수정하기
	
	void plusHit(String parameter);								// 조회수 증가 세션에 근거함
	
	void insertFakeData(Map<String,String> map);				// 포스트 더미데이터 삽입 600건
	
	
	  
	
//	Spring Board Comment Service part
	

	void insertComment(CommentVO commentVo);					// 댓글 삽입

	int getCommentCount(String sb_idx);							// 총 댓글 갯수
	  
	ArrayList<CommentVO> getComments(Map<String, Integer> nums);// 한 페이지 분량의 댓글 가져오기

	void updateComment(Map<String, String> map);				// 댓글 수정하기

	void deleteComment(String comment_idx);						// 댓글 삭제하기

	String getCommentPassword(String string);					// 당 댓글의 비밀번호 가져오기

	void insertFakeCommentData(Map<String, String> map);		// 댓글 더미데이터 삽입

	
//	log
	
	void setVisitLog(String clientIP);							// 방문자 아이피 남기기


	
	
	}