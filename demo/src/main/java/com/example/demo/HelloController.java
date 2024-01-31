package com.example.demo;

import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


//java 객체를 json 형태로 바꿔준다

//http://localhost:8080/getUserInfo
//기보포트번호가 8080
//오라클을 express 버전을 설치하면 홈페이지를 만들어서 내부에서 8080을 쓰고 있어서 포트번호가 충돌날 수 있다.(address already)
@CrossOrigin("*")
@RestController
@RequestMapping(value="")
// /hello/~~~~~~ 이 컨트롤러가 다 처리한다
public class HelloController {
	
	//단축키 - ctrl-shift-o
	@GetMapping("/getUserInfo")
	public HashMap<String, String> getUserInfo()
	{
		HashMap<String,String> map=new HashMap<String,String>();
		//컬렉션클래스, 배열, Map, json, SortedList...
		//배열의 요소는 index를 통해 읽고 쓸 수 있다.
		//HashMap, Dictionary, json 동일한 구조
		//키와 값 쌍으로 구성되는 데이터를 저장해서 
		//데이터를 읽고 쓸 때 키 값으로 찾아서 읽고 쓴다.
		//{"name":"홍길동", "phone":"010-9000-0001", "address":"서울시"}
		
		map.put("name", "홍길동");
		map.put("phone", "010-9000-0001");
		map.put("address", "서울시 관악구");
		
		return map;
	}
	
	//정보를 주고 받는 방식 - get 방식
	//  /getUserInfo?userid=test&username=홍길동
	
	//새로운 방식
	//  /getUserInfo/test
	
	//post방식은 => form 태그에 method = "POST"로 바꿔야 한다.
	
	//방법1)/add1?x=5&y=7  {x:5, y:7, result:12}-get
	//방법2) /add2/5/7  {x:5, y:7, result:12}-get
	//방법3) add3  {x:5, y:7, result:12}-post
	
	//add1?x=5&y=7
	@GetMapping("/add1")
	public HashMap<String, Object> add1(HttpServletRequest request, @RequestParam("x") int x, @RequestParam("y") int y) 
	{
		//HttpServletRequest 객체에 담아온다.
		//int x=Integer.parseInt(request.getParameter("x"));
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("x", x);
		map.put("y", y);
		
		map.put("result", x+y);
		
		return map;
	}
	
	//add2/5/7
	@GetMapping("/add2/{x}/{y}")
	public HashMap<String, Object> add2(HttpServletRequest request, @PathVariable("x") int x, @PathVariable("y") int y) 
	{
		//HttpServletRequest 객체에 담아온다.
		//int x=Integer.parseInt(request.getParameter("x"));
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("x", x);
		map.put("y", y);
		
		map.put("result", x+y);
		
		return map;
	}
	
	@PostMapping("/add3")
	public HashMap<String, Object> add3(HttpServletRequest request, @RequestParam("x") int x, @RequestParam("y") int y) 
	{
		//HttpServletRequest 객체에 담아온다.
		//int x=Integer.parseInt(request.getParameter("x"));
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("x", x);
		map.put("y", y);
		
		map.put("result", x+y);
		
		return map;
	}
	
	//@RequestBody - json으로 받아라!
	@PostMapping("/add4")
	public HashMap<String, Object> add4(@RequestBody HashMap<String, String> map) 
	{
		//HttpServletRequest 객체에 담아온다.
		//int x=Integer.parseInt(request.getParameter("x"));
		HashMap<String, Object> resultMap=new HashMap<String, Object>();
		
		int x=Integer.parseInt(map.get("x").toString());
		int y=Integer.parseInt(map.get("y").toString());
		
		
		resultMap.put("x", x);
		resultMap.put("y", y);
		
		resultMap.put("result", x+y);
		
		return resultMap;
	}
	
	//1.30과제.html
	@PostMapping("/payment")
	public HashMap<String, Object> payment(@RequestBody HashMap<String, String> map) 
	{
		//HttpServletRequest 객체에 담아온다.
		//int x=Integer.parseInt(request.getParameter("x"));
		HashMap<String, Object> resultMap=new HashMap<String, Object>();
		
		int hours=Integer.parseInt(map.get("hours").toString());
		int hourlyWage=Integer.parseInt(map.get("hourlyWage").toString());
		
		
		resultMap.put("hour", hours);
		resultMap.put("hourlyWage", hourlyWage);
		
		resultMap.put("result", hours*hourlyWage);
		
		return resultMap;
	}
	
	//@RequestBody  데이터를 client가 json 형태로 보낼 때
	//              json 데이터를 받아서 자바 객체로 전환 과정을 거친다.
	//              HashMap이나 Dto(Data Transfer Object) 클래스 
	//              DB테이블 필드와 거의 1:1,
	//              3개의 테이블을 조인해서 필요한 필드만큼 만들 수 있다.
	// 클라이언트로부터 파라미터(정보)를 받아올 때 보통 DTO를 사용한다. 
	// name=홍길동&age=12 ==> {"name":"홍길동", "age":12}
	// Restful API - 데이터를 주고 받을 때 표준 xml이나 json이다.
	// xml - 실제 데이터를 가져오는 parsing 과정이 별도로 필요하다.(파서 프로그램도
	// 많다. 점점 시장에서 자리를 잃고 있다. json으로 거의 통일되고 있는 상황)
	
	//1.30과제_답안.html
	@PostMapping("/getPayment")
	HashMap<String, Object> getPayment(
			@RequestBody HashMap<String, String> param)
	{
		int work_time=Integer.parseInt(param.get("work_time"));
		int per_pay=Integer.parseInt(param.get("per_pay"));
		String name=param.get("name");
		
		int pay=work_time*per_pay;
		
		HashMap<String, Object> resultMap=new HashMap<String, Object>();
		resultMap.put("result", "OK");
		resultMap.put("msg", String.format("%s 님의 주급은 %d입니다", name,pay));
		return resultMap;
	}
}
