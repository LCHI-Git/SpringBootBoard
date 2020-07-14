package com.web.springBoard.controller;


import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.springBoard.application.BoardService;

@RestController
public class BoardRestController {
  @Autowired
  BoardService boardService;

//	checkPW return 'true' or 'false' 
  @PostMapping("/checkPassword") 
  public String checkPostPasswrod(@RequestBody Map<String,String> data) {
	 
	  String userInputPassword = data.get("userInputPassword");
	  String postPassword = boardService.getPassword(data.get("sb_idx"));
	  
	  if(userInputPassword.equals(postPassword)) return "true";
	  else return "false";
   
  }
  
  
@PostMapping("/checkCommentPassword") 
public String checkCommentPasswrod(@RequestBody Map<String,String> data) {
	
	  System.out.println(data.get("comment_password"));
	 
	  String userInputPassword = data.get("userInputPassword");
	  String commentPassword = boardService.getCommentPassword(data.get("comment_idx"));
	  System.out.println(commentPassword);
	  if(userInputPassword.equals(commentPassword)) return "true";
	  else return "false";

}
  
 }