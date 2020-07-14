package com.web.springBoard.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.springBoard.domain.BoardRepository;
import com.web.springBoard.vo.CommentVO;
import com.web.springBoard.vo.PostVO;

@Service
public class BoardServiceImpl implements BoardService {
  
	@Autowired
	private BoardRepository boardRepository;

//	Spring Board imple
	
  @Override
	public void insertFakeData(Map<String,String> map) {
		boardRepository.insertFakeData(map);
	}

	@Override
	public int getTotalCount() {
		return boardRepository.getTotalCount();
}

	@Override
	public ArrayList<PostVO> getPostArr(Map<String, Integer> seNum) {
		return boardRepository.getPostArr(seNum);
	}

	@Override
	public void insertPost(PostVO postVo) {
		boardRepository.insertPost(postVo);
	}

	@Override
	public String getPassword(String sb_idx) {
		return boardRepository.getPassword(sb_idx);
	}

	@Override
	public void deletePost(String sb_idx) {
		boardRepository.deletePost(sb_idx);
	}

	@Override
	public PostVO getPostByIdx(String sb_idx) {
		return boardRepository.getPostByIdx(sb_idx);
	}

	@Override
	public void updatePost(PostVO postVo) {
		boardRepository.updatePost(postVo);
	}
	
	@Override
	public void plusHit(String sb_idx) {
		boardRepository.plusHit(sb_idx);
	}

	
	
	
//	Spring Board Comment imple
	
	@Override
	public int getCommentCount(String sb_idx) {
		return boardRepository.getCommentCount(sb_idx);
	}

	@Override 
	public ArrayList<CommentVO> getComments(Map<String, Integer> nums) {
		return boardRepository.getComments(nums);
	}

	@Override
	public void updateComment(Map<String, String> coms) {
		boardRepository.updateComment(coms);
	}

	@Override
	public void deleteComment(String comment_idx) {
		boardRepository.deleteComment(comment_idx);
	}

	@Override
	public void insertComment(CommentVO commentVo) {
			boardRepository.insertComment(commentVo);
	}

	@Override
	public String getCommentPassword(String comment_idx) {
		return boardRepository.getCommentPassword(comment_idx);
	}
	
	

	@Override
	public void insertFakeCommentData(Map<String, String> map) {
		boardRepository.insertFakeCommentData(map);
	}

//	log
	
	@Override
	public void setVisitLog(String clientIP) {
		boardRepository.setVisitLog(clientIP);
	}


}
