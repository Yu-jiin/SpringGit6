package com.itwillbs.controller;

import java.net.http.HttpHeaders;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	// http://localhost:8088/test7/1000
	// http://localhost:8088/test7/555
	// http://localhost:8088/test7/itwill
	// http://localhost:8088/1000
	// http://localhost:8088/test7
	@RequestMapping(value = "/test7/{bno}", method = RequestMethod.GET)
	public int test7(@PathVariable("bno") int bno) throws Exception{
		logger.info(" test7 실행 ");
		
		logger.info(" bno : " + bno);
		return 0;
	}
	
	
	// http://localhost:8088/test/add
	// ajax 방식으로 전달된 데이터 저장
	@RequestMapping(value = "/test/add", method = RequestMethod.POST)
	public void testADD(@RequestBody MemberVO vo) throws Exception{
		// @RequestBody : JSON 타입 문자열 데이터를 객체의 형태로 형변환 
		logger.info(" testADD() 실행 ");
		logger.info("vo >> " + vo);
		// 서비스 객체 주입 -> 메서드 호출 -> 결과 리턴 -> view 페이지 전달
		
		
	}
	
	
	// ResponseEntity 클래스 : @RestController 에서 별도의 view페이지가 없는 데이터를 처리할 때
	// 						   발생하는 예외상황을 처리하기위한 클래스
	
	// HTTP 상태코드 (status)
	// 100번대 : 정보응답 / 현재 데이터의 처리중인 상태
	//	-100 : 데이터의 일부를 서버가 받은 상태 
	
	// 200번대 : 성공응답 / 정상적인 응답 
	// 	-200 : 에러없이 정상처리
	// 	-201 : 요청의 처리가 성공적, 그 결과로 새로운 리소스가 생성됨
	//	-204 : 정상처리 되었으나, 서버에서 보내줄 데이터가 없음 
	
	// 300번대 : 리다이렉션 메세지 / 다른 URL 처리상태
	//	-301 : 요청된 페이지가 새 URL으로 변경 
	//	-304 : 이미 기존의 데이터정보와 변경이 없음
	
	// 400번대 : 클라이언트 오류응답 / 서버에서 인식이 불가능
	//	-400 : 전송한 데이터에 문제가 있어서 서버가 인지불가 (ajax)
	//	-401 : 인증되지 않음 
	//	-403 : 서버에서 허락 X
	//	-404 : URL에 해당하는 리소스를 찾을 수 없음
	//	-405/406 : 전송 방식에 따른 접근 허용 여부 (REST)
	
	// 500번대 : 서버 오류응답 / 서버 내부의 문제
	//	-500 : 서버 내부에서 문제가 발생 (컴파일 에러/ 실행 에러)
	// 	-502 : 게이트웨이/프록시 상태 문제 (과부하)
	//	-503 : 서버가 요청을 처리할 상태 X (일시적 과부하/서비스 중단)
	
	
	// => ResponseEntity 객체가 처리 결과의 데이터 + HTTP 상태코드 전달 
	
	/*
	 * // http://localhost:8088/test8
	 * 
	 * @RequestMapping(value = "/test8", method = RequestMethod.GET) public String
	 * test8() throws Exception{ logger.info(" test8() 호출 ");
	 * 
	 * 
	 * return "I want to go home"; // 처리되는 데이터만 확인, 상태확인 불가능
	 * 
	 * }
	 */
	
	// http://localhost:8088/test8	
	@RequestMapping(value = "/test8", method = RequestMethod.GET)
	public ResponseEntity<Void> test8() throws Exception{
		logger.info(" test8() 호출 ");
		
		
		// return "I want to go home"; // 처리되는 데이터만 확인, 상태확인 불가능
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	// http://localhost:8088/test9	
	@RequestMapping(value = "/test9", method = RequestMethod.GET)
	public ResponseEntity<List<MemberVO>> test9() throws Exception{
		logger.info(" test9() 호출 ");
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		
		MemberVO vo = null;
		for(int i =0; i<10; i++) {
			vo = new MemberVO();
			vo.setUserid("home"+i);
			vo.setUserpw("1234"+i);
			vo.setUsername("go home"+i);
			vo.setUseremail("plz@gohome.com"+i);			
			
			memberList.add(vo);
		}

		return new ResponseEntity<List<MemberVO>>(memberList,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// http://localhost:8088/test10
	@RequestMapping(value = "/test10", method = RequestMethod.GET)
	public ResponseEntity test10() throws Exception{
		logger.info(" test10() 호출 ");
		org.springframework.http.HttpHeaders respHeaders = new org.springframework.http.HttpHeaders();
		respHeaders.add("Content-Type", "text/html; charset=utf-8");
	
		String result = "<script>";
		result += " alert('test message'); ";
		result += " location.href='http://www.naver.com'; ";
		result += "</script>";
		
		return new ResponseEntity(result,respHeaders,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
