package com.web.springBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.web.springBoard.vo.PostVO;

@SpringBootApplication
public class SpringBoardApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBoardApplication.class, args);
		
		
	}

}
