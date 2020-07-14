package com.web.springBoard.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.web.springBoard.vo.CommentVO;
import com.web.springBoard.vo.PostVO;

@Mapper
@Repository
public interface BoardRepository {
  
//  Spring Board Repository
	int getTotalCount();

	ArrayList<PostVO> getPostArr(Map<String, Integer> seNum);
  
	void insertPost(PostVO postVo);

	String getPassword(String sb_idx);

	void deletePost(String sb_idx);

	PostVO getPostByIdx(String sb_idx);

	void updatePost(PostVO postVo);
	
	void plusHit(String sb_idx);
  
	void insertFakeData(Map<String,String> map);

	
//	Spring Board Comment Repo
	
	void insertComment(CommentVO commentVo);

	int getCommentCount(String sb_idx);

	ArrayList<CommentVO> getComments(Map<String, Integer> nums);

	void updateComment(Map<String, String> coms);

	void deleteComment(String comment_idx);
  
	String getCommentPassword(String comment_idx);

	void insertFakeCommentData(Map<String, String> map);

	
//	log
	void setVisitLog(String clientIP);


}