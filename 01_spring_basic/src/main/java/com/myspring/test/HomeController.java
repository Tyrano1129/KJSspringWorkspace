package com.myspring.test;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	_01Member test1 = new _01Member();
	
	@Autowired
	_01Member test2;
	@Autowired
	_01Member test3;
	@Autowired
	_02UserDAO userDAO;
	
	@Autowired
	_03ClientDAO clientDAO;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
		System.out.println(">> 1단계 >>");
		System.out.println("test1의 주소 = " + test1);
		
		System.out.println("test2의 주소 = " + test2);
		System.out.println("test2의 주소 = " + test3);
		
		test2.setNum(300);
		test2.getNum();
		test3.getNum();
		
		System.out.println(">> 2단계 >>");
		userDAO.print();
		
		System.out.println(">> 3단계 >>");
		String schoolName = clientDAO.getSchoolName();
		System.out.println("schoolName = " + schoolName);
		
		ArrayList<String> clist = clientDAO.getClientList();
		for(String name : clist) {
			System.out.println(name);
		}
		
		System.out.println("------------------------------");
		
		HashMap<String,String> cMap1 = clientDAO.getClientMap1();
		for(String key : cMap1.keySet()) {
			System.out.println("key = " + key + " value = " + cMap1.get(key));
		}
		HashMap<String,_03Client> cMap2 = clientDAO.getClientMap2();
		for(String key : cMap2.keySet()) {
			System.out.println("key = " + key + " value = " + cMap2.get(key));
		}
		return "home";
	}
	
}







