package com.web.springBoard.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.springBoard.application.BoardService;
import com.web.springBoard.utils.Utils;
import com.web.springBoard.vo.CommentListVO;
import com.web.springBoard.vo.CommentVO;
import com.web.springBoard.vo.PostVO;
import com.web.springBoard.vo.PostVOList;


@Controller
public class HomeController {
	
	@Autowired
	BoardService boardService;
	PostVO postVo;
	CommentVO CommentVo;
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String index(HttpSession session, HttpServletRequest req, HttpServletResponse res, Model model) {
//		�湮���
//		������ visitedPost attr�� ������ ���� attr�� ar�� �����Ѵ�
		if(session.getAttribute("visitedPost")==null) {
			session.setAttribute("visitedPost", new ArrayList<String>());
		}
//		Utils Ŭ�������� ������� �ּҿ� �� ������Ʈ ��¥ ���信 ���� ��¥�� �����´�.
		String userIP = Utils.getClientIP(req);
		String now = Utils.getSbDate();
		String logData = "IP : "+userIP+" DATE : "+now;
		
//		������ visitLog attr�� ������ visitLog:logData �� �߰��Ѵ�.
//		�׸��� DB�� �����Ѵ�.
		if(session.getAttribute("visitLog")==null) {
			session.setAttribute("visitLog", logData);
			boardService.setVisitLog(logData);
		}
		else {
//			������ logData�� 1���� �ð����� ���� �� �����Ѵ�.
			String beforeVisit = (String)session.getAttribute("visitLog");
			if(!beforeVisit.equals(logData)) {
				session.setAttribute("visitLog", logData);
				boardService.setVisitLog(logData);
			}
		}
		
		
//		�Խ��� �ε�
		int currentPage; // ���������� ����
		int totalCount = boardService.getTotalCount(); // �� ���� ����
		try {
//			Ŭ���̾�Ʈ ���������� ��������
			currentPage = Integer.parseInt(req.getParameter("currentPage"));
		}
		catch(NumberFormatException e) {
//			NumberFormatException �ô� ���������� == 1
			currentPage = 1;
		}
		
		PostVOList postList = new PostVOList();  // �� �������� ����Ʈ �Խù� ����Ʈ Ŭ����
		postList.initPostList(10, totalCount, currentPage);  // �� ������ �Խù� �����
//																���ڷδ� (ǥ���� ����, �� �Խù�����, ����������)
//		Ž���� ���۹�ȣ�� ǥ���� �Խù� ������ map���� �����.
		Map<String, Integer> seNum = new HashMap<String, Integer>(); 
		seNum.put("startNo", postList.getStartNo());
		seNum.put("pageSize", postList.getPageSize());
		
		ArrayList<PostVO> postAr = boardService.getPostArr(seNum);// DB���� ���ϴ� ������ �Խù� ������
		postList.setPostList(postAr);// PostList class�� set�Լ��� ����Ʈ�� ����
		model.addAttribute("postList", postList);// model�� ����
		return "index";
	}
	
	
	//�� �� ��ȸ
		@RequestMapping(value="/contentView",method = RequestMethod.GET)
		public String contentView(HttpSession session, HttpServletRequest req, HttpServletResponse res, Model model) {
			
			ArrayList<String> arr = (ArrayList)session.getAttribute("visitedPost");
			
			if(arr.size()==0){arr.add(req.getParameter("sb_idx"));}
			
			else {
				boolean flag = false;
				for(int i=0; i<arr.size(); i++) {
					if(arr.get(i).equals(req.getParameter("sb_idx"))) {
						flag = true;
					}
				}
				if(flag==false) {
					arr.add(req.getParameter("sb_idx"));
					boardService.plusHit(req.getParameter("sb_idx"));
				}
			}
			session.setAttribute("visitedPost", arr);
			String sb_idx = req.getParameter("sb_idx");
			PostVO postVo = boardService.getPostByIdx(sb_idx);
			
			int currentPage;
			int totalCount = boardService.getCommentCount(sb_idx);
			
			try {
				currentPage = Integer.parseInt(req.getParameter("currentPage"));
			}
			catch(NumberFormatException e) {
				currentPage = 1;
			}
			
			CommentListVO commentList = new CommentListVO();
			commentList.initCommentList(10, totalCount, currentPage);
			
			Map<String, Integer> nums = new HashMap<String, Integer>();
			
			nums.put("sb_idx", Integer.parseInt(sb_idx));
			nums.put("startNo", commentList.getStartNo());
			nums.put("pageSize", commentList.getPageSize());
			
			
			ArrayList<CommentVO> comments = boardService.getComments(nums);
			commentList.setCommentList(comments);
			
			model.addAttribute("postVo",postVo);
			model.addAttribute("commentList",commentList);
			return "contentView";
			
		}
	
	
	
	@RequestMapping(value="/CommentSave",method = RequestMethod.POST)
	public String CommentSave(HttpServletRequest req, CommentVO commentVo) {
		
		
		commentVo.setComment_writeDate(Utils.getSbDate());
		commentVo.setSb_idx(Integer.parseInt(req.getParameter("sb_idx")));
		boardService.insertComment(commentVo);
		
		return "redirect:contentView?sb_idx="+req.getParameter("sb_idx");
		}
	
	
	//��� ����
		@RequestMapping(value="/updateComment",method = RequestMethod.GET)
		public String updateComment(HttpServletRequest req, HttpServletResponse res, Model model) {
			
			String comment_content = req.getParameter("updateText");
			String comment_idx = req.getParameter("comment_idx");
			String sb_idx = req.getParameter("sb_idx");
			String currentPage = req.getParameter("currentPage");
			System.out.println("��� ���� : "+sb_idx+" , "+currentPage);
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("comment_content",comment_content);
			map.put("comment_idx",comment_idx);
			
			boardService.updateComment(map);
			return "redirect:contentView?sb_idx="+sb_idx+"&currentPage="+currentPage;
		
		}
		
		//��� ����
		@RequestMapping(value="/deleteComment",method = RequestMethod.GET)
		public String deleteComment(HttpServletRequest req, HttpServletResponse res, Model model) {
			
			String comment_idx = req.getParameter("comment_idx");
			System.out.println("idx:" +comment_idx);
			String sb_idx = req.getParameter("sb_idx");
			String currentPage = req.getParameter("currentPage");
			System.out.println("��� ���� : "+sb_idx+" , "+currentPage);
			
			boardService.deleteComment(comment_idx);
			return "redirect:contentView?sb_idx="+sb_idx+"&currentPage="+currentPage;
		
		}
	
	
	@RequestMapping(value="/writePost",method = RequestMethod.GET)
	public String writePost() {
		return "writePost";
	}
	

	@RequestMapping(value="/savePost",method = RequestMethod.POST)
	public String savePost(HttpServletRequest req, Model model, PostVO postVo) {
		postVo.setSb_writeDate(Utils.getSbDate());
		
		postVo.setSb_content(postVo.getSb_content().replace("\r\n", "<br>"));
		
		boardService.insertPost(postVo);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/deletePost",method = RequestMethod.GET)
	public String deletePost(HttpServletRequest req) {
		boardService.deletePost(req.getParameter("sb_idx"));
		return "redirect:/";
	}
	
	@RequestMapping(value = "/editPostView",method = RequestMethod.GET)
	public String editPostView(HttpServletRequest req,Model model) {
		String sb_idx = req.getParameter("sb_idx");
		String currentPage = req.getParameter("currentPage");
		postVo = boardService.getPostByIdx(sb_idx);
		model.addAttribute("postVo",postVo);
		model.addAttribute("currentPage",currentPage);
		
		return "edit";
	}
	
	@RequestMapping(value = "/editPost",method = RequestMethod.POST)
	public String editPost(HttpServletRequest req,Model model,PostVO postVo) {
		
		
		int sb_idx = Integer.parseInt(req.getParameter("sb_idx"));
		
		String currentPage = req.getParameter("currentPage");
		
		postVo.setSb_idx(sb_idx);
		
		postVo.setSb_writeDate(Utils.getSbDate());
		
		postVo.setSb_content(postVo.getSb_content().replace("\r\n", "<br>"));
		
		boardService.updatePost(postVo);
		
		return "redirect:/?currentPage="+currentPage;
	}
	
	
	@RequestMapping(value="/insertFakeData",method = RequestMethod.GET)
	public String insertFakeData() {
		
		Date dt = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd(HH:mm)");
		
		String now = sdf.format(dt);
		
		System.out.println(now);
		
		
		
		for (int i = 1; i < 600; i++) {
			
			Map<String, String> map = new HashMap<String, String>();
			
			map.put("num", i+"");
			map.put("date", now);
			
			boardService.insertFakeData(map);
		}
		
		return "index";
	}
	@RequestMapping(value="/insertFakeCommentData",method = RequestMethod.GET)
	public String insertFakeCommentData() {
		
		Date dt = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd(HH:mm)");
		
		String now = sdf.format(dt);
		
		for (int i = 1; i < 8; i++) {
			
			Map<String, String> map = new HashMap<String, String>();
			
			map.put("num", i+"");
			map.put("date", now);
			
			boardService.insertFakeCommentData(map);
		}
		return "index";
	}
	
}


