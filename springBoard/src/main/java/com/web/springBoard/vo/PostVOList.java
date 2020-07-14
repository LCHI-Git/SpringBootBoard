package com.web.springBoard.vo;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

public class PostVOList {
	private ArrayList<PostVO> postList = new ArrayList<PostVO>();  // �ڽ��� �ν��Ͻ�
	private int pageSize = 10;	// �� �������� �Խù� ����
	private int totalCount = 0;	// �� �Խù��� ����
	private int totalPage = 0;	// �� �������� ����
	private int currentPage = 1;// ���� ������
	private int startNo = 0;	// �� �������� �Խù� ���۹�ȣ IDX
	private int endNo = 0;		// �� �������� �Խù� �� ��ȣ 
	private int startPage = 0;	// ���� �������� ���� ���� ��������ȣ
	private int endPage = 0;	// ���� �������� ���� �� ������ ��ȣ
	
	private void calculator() {	// ��� ����¡ ó����
		totalPage = (totalCount - 1) / pageSize + 1; 
		currentPage = currentPage > totalPage ? totalPage : currentPage;
		startNo = (currentPage - 1) * pageSize;  
		endNo = startNo + pageSize;  
		endNo = endNo > totalCount ? totalCount : endNo;  
		startPage = (currentPage - 1) / 10 * 10 + 1;  
		endPage = startPage + 9;
		endPage = endPage > totalPage ? totalPage : endPage;
	}
	
	public void initPostList(int pageSize, int totalCount, int currentPage) {
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		calculator();
	}

	public ArrayList<PostVO> getPostList() {
		return postList;
	}

	public void setPostList(ArrayList<PostVO> postList) {
		this.postList = postList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "PostVOList [postList=" + postList + ", pageSize=" + pageSize + ", totalCount=" + totalCount
				+ ", totalPage=" + totalPage + ", currentPage=" + currentPage + ", startNo=" + startNo + ", endNo="
				+ endNo + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
	
}

