package com.mysql.basic.controller;

import java.net.http.HttpRequest;
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

import com.mysql.basic.entity.Member;
import com.mysql.basic.repository.MemberDAO;

@Controller
public class MemberController {
	
	
	@Autowired
	MemberDAO memberDAO;
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	// get, post, put, delete 모든 값들이 허용 가능하다.
	@RequestMapping(value="/member/userMenu")
	public String UserMenu() {
		return "member/userMenu";
	}
	@GetMapping("/member/list")
	public String list(Model model) {
		ArrayList<Member> memberList = memberDAO.getMemberList();
		model.addAttribute("memberList",memberList);
		return "member/list";
	}
	@GetMapping("/member/index")
	public String index() {
		return "member/index";
	}
	@GetMapping("/member/joinForm")
	public String join() {
		return "member/joinForm";
	}
	@PostMapping("/member/joinPro")
	public String joinPro(Member member) {
		System.out.println("member = " + member);
		memberDAO.memberJoin(member);
		return "redirect:/member/list";
	}
	@GetMapping("/member/loginForm")
	public String login() {
		return "/member/loginForm";
	}
	@PostMapping("/member/loginPro")
	public String loginPro(Member member,Model model) {
		System.out.println("member = " + member);
		int check = memberDAO.checkMember(member);
		model.addAttribute("check", check);
		model.addAttribute("id",member.getId());
		return "member/loginPro";
	}
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "member/logoutPro";
	}
	@GetMapping("/member/modifyForm")
	public String modifyForm(HttpSession session, Model model) {
		if(session.getAttribute("log") == null) {
			return "/";
		}
		String id = (String)session.getAttribute("log");
		Member member = memberDAO.getOneMember(id);
		model.addAttribute("member",member);
		
		return "member/modifyForm";
	}
	@PostMapping("/member/modifyPro")
	public String modifyPro(Member member,HttpSession session) {
		if(session.getAttribute("log") == null) {
			return "member/index";
		}
		System.out.println("member = " + member);
		String id = (String)session.getAttribute("log");
		member.setId(id);
		memberDAO.updateMember(member);
		return "redirect:/member/list";
	}
	@GetMapping("/member/delete")
	public String delete(HttpSession session) {
		if(session.getAttribute("log") == null) {
			return "member/index";
		}
		String id = (String)session.getAttribute("log");
		memberDAO.memberDelete(id);
		return "redirect:/member/list";
	}
}
