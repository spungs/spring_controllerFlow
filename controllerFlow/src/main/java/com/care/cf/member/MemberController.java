package com.care.cf.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.cf.member.dto.MemberDTO;
import com.care.cf.member.repository.MemberDAO;
import com.care.cf.member.service.MemberService;

@Controller
public class MemberController {
	//http://localhost:8085/cf/index
	
	@RequestMapping("index") // 이렇게 value 속성을 안쓰고 속성값만 쓰면 value로 알아먹음, method를 안쓰면 get, post 다 받음
	public void index() {
		System.out.println("index 페이지");
	}
	/*
	 	@RequestMapping("index")
		public void index() {}
	 	================================
 		@RequestMapping("index")
		public void index() {
			return "index"; // @RequestMapping value의 값이 return 값과 동일하면 생략가능
		}
	 */
	@GetMapping("login")
	public void login() {
		System.out.println("로그인 페이지 처리");
	}
	
	@PostMapping("login")
	public String login(String id, String pw, Model model, RedirectAttributes ra) {
		System.out.println("로그인 페이지에서 입력한 데이터들");
		System.out.println("받아서 처리");
		System.out.println("아이디 : " + id);
		System.out.println("비밀번호 : " + pw);
		
		/*
			return
			 - 로그인 성공 : index
		 	 - 로그인 실패 : login
		 */
		if("admin".equals(id) && "1234".equals(pw)) {
//			model.addAttribute("msg", "로그인 성공");
//			return "forward:index";
//			return "index";
			ra.addFlashAttribute("msg", "login success");
			return "redirect:index";
			
			
			/*
			 * return "index"; 
			 * viewResolver에게 전달하여 jsp파일을 찾아 실행 후 결과를 클라이언트에게 응답.
			 */
			/*
			 * return "redirect:index";
			 * 요청 경로를 클라이언트에게 응답.
			 * 요청 경로를 받은 클라이언트가 서버로 재요청함.
			 */
			/*
			 * return "forward:index";
			 * 요청 경로를 서버 내부에서 요청함.
			 * 서버 내부에서 Mapping을 찾아 메소드 실행.
			 */
		}
		model.addAttribute("msg", "로그인 실패");
		return "login";
	}
	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}
	
//	@RequestMapping(value = "register", method = RequestMethod.POST)
//	public String register(
//			@RequestParam(value = "id")String i, 
//			@RequestParam("pw")String p, 
//			@RequestParam("name")String n) {
//		System.out.println("아이디 : " + i);
//		System.out.println("비밀번호 : " + p);
//		System.out.println("이름 : " + n);
//		
//		return "register";
//	}
	
//	@RequestMapping(value = "register", method = RequestMethod.POST)
//	public String register(HttpServletRequest req) {
//		System.out.println("아이디 : " + req.getParameter("id"));
//		System.out.println("비밀번호 : " + req.getParameter("pw"));
//		System.out.println("이름 : " + req.getParameter("name"));
//		
//		return "register";
//	}
	
	@Autowired private MemberService service;
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(MemberDTO member, Model model) {
		System.out.println("아이디 : " + member.getId());
		System.out.println("비밀번호 : " + member.getPw());
		System.out.println("이름 : " + member.getName());
	 	String result = service.register(member);
	 	if(result.equals("회원 가입 성공")) {
	 		model.addAttribute("msg", result);
	 		return "index";
	 	}
	 	
	 	model.addAttribute("msg", result);
		return "register";
	}
	
	// 회원 목록
	@RequestMapping("list")
	public void list(Model model) {
		model.addAttribute("members", service.list());
	}
	
	
}
