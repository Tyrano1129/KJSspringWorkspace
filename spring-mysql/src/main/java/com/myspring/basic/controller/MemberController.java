package com.myspring.basic.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myspring.basic.entity.Member;
import com.myspring.basic.mapper.MemberMapper;


@Controller
public class MemberController {
	
	@Autowired
	private MemberMapper mapper;
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	@RequestMapping("/")
	public String main() {
		return "member/index";
	}
	@GetMapping("/member/list")
	public String list(Model model) {
		ArrayList<Member> list = (ArrayList<Member>)mapper.getList();
		model.addAttribute("memberList",list);
		return "member/list";
	}
	@GetMapping("/member/index")
	public String index() {
		return "member/index";
	}
	@GetMapping("/member/userMenu")
	public String userMenu() {
		return "member/userMenu";
	}
	@GetMapping("/member/joinForm")
	public String joinForm() {
		return "member/joinForm";
	}
	@PostMapping("/member/joinPro")
	public String joinPro(Member member) {
		mapper.memberInsert(member);
		return "redirect:/member/list";
	}
	@GetMapping("/member/loginForm")
	public String loginForm() {
		return "member/loginForm";
	}
	@PostMapping("/member/loginPro")
	public String loginPro(Member member, HttpSession session) {
		Member m = mapper.checkMember(member);
		if(m.getNum() == 0) {
			return "member/loginForm";
		}
		session.setAttribute("log",m.getId());
		return "redirect:/member/userMenu";
	}
}







