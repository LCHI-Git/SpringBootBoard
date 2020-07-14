package com.web.springBoard.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.web.springBoard.vo.CommentVO;
import com.web.springBoard.vo.PostVO;

public interface BoardService {
	
//	Spring Board Service part
	
	int getTotalCount();
	
	ArrayList<PostVO> getPostArr(Map<String, Integer> seNum);	// �� ������ �з� ����Ʈ ������
	
	void insertPost(PostVO postVo);								// ����Ʈ ����
	
	String getPassword(String sb_idx);							// ���� ����Ʈ�� ��й�ȣ ������

	void deletePost(String parameter);							// ���� ����Ʈ �����ϱ�

	PostVO getPostByIdx(String sb_idx);							// �ش� ���ڿ� �ش��ϴ� ����Ʈ ������

	void updatePost(PostVO postVo);								// ����Ʈ �����ϱ�
	
	void plusHit(String parameter);								// ��ȸ�� ���� ���ǿ� �ٰ���
	
	void insertFakeData(Map<String,String> map);				// ����Ʈ ���̵����� ���� 600��
	
	
	  
	
//	Spring Board Comment Service part
	

	void insertComment(CommentVO commentVo);					// ��� ����

	int getCommentCount(String sb_idx);							// �� ��� ����
	  
	ArrayList<CommentVO> getComments(Map<String, Integer> nums);// �� ������ �з��� ��� ��������

	void updateComment(Map<String, String> map);				// ��� �����ϱ�

	void deleteComment(String comment_idx);						// ��� �����ϱ�

	String getCommentPassword(String string);					// �� ����� ��й�ȣ ��������

	void insertFakeCommentData(Map<String, String> map);		// ��� ���̵����� ����

	
//	log
	
	void setVisitLog(String clientIP);							// �湮�� ������ �����


	
	
	}