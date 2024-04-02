package com.itwillbs.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.MemberVO;

// @RestController : 해당 클래스를 REST 컨트롤러 역할수행
// 			=> 모든 메서드의 동작을 @ResponseBody 생략해놓은 상태 

@RestController
public class SampleRestController1 {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleRestController1.class);
	
	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public @ResponseBody String test1() {
		// @ResponseBody : 데이터를 뷰페이지가 아니라 데이터(리소스)로 처리
		logger.info(" test() 실행 "); 
		return "ITWILL";
	}
	
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public void test2() {
		logger.info(" test2() 실행 "); 
	}
	
	@RequestMapping(value = "/test3", method = RequestMethod.GET)
	public Date test3() {
		logger.info(" test3() 실행 "); 
		return new Date();
	}
	
	@RequestMapping(value = "/test4", method = RequestMethod.GET)
	public MemberVO test4() {
		logger.info(" test4() 실행 "); 
		MemberVO vo = new MemberVO();
		vo.setUserid("admin");
		vo.setUserpw("12345");
		vo.setUsername("관리자");
		vo.setUseremail("admin@");
		vo.setRegdate(new Timestamp(System.currentTimeMillis()));
		vo.setUpdatedate(new Timestamp(System.currentTimeMillis()));
		return vo;
	}

	// JSON 배열 (List)
	
	@RequestMapping(value = "/test5", method = RequestMethod.GET)
	public List<MemberVO> test5() {
		logger.info(" test5() 실행 "); 
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		for(int i=0; i<10; i++) {
		MemberVO vo = new MemberVO();
		vo.setUserid("admin");
		vo.setUserpw("12345");
		vo.setUsername("관리자");
		vo.setUseremail("admin@");
		vo.setRegdate(new Timestamp(System.currentTimeMillis()));
		vo.setUpdatedate(new Timestamp(System.currentTimeMillis()));
		memberList.add(vo);
		}
		return memberList;
	}
	
	@RequestMapping(value = "/test6", method = RequestMethod.GET)
	public List<MemberVO> test6() {
		logger.info(" test5() 실행 "); 
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		for(int i=0; i<10; i++) {
			MemberVO vo = new MemberVO();
			vo.setUserid("admin");
			vo.setUserpw("12345");
			vo.setUsername("관리자");
			vo.setUseremail("admin@");
			vo.setRegdate(new Timestamp(System.currentTimeMillis()));
			vo.setUpdatedate(new Timestamp(System.currentTimeMillis()));
			memberList.add(vo);
		}
		return memberList;
	}
	
	
	
	
	
	
	
	
}
