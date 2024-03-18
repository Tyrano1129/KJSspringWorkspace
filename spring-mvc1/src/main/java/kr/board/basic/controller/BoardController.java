package kr.board.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.board.basic.entity.Board;
import kr.board.basic.mapper.BoardMapper;

@Controller
public class BoardController {
	@Autowired
	private BoardMapper mapper;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "template";
	}
	@GetMapping("/boardList.do")
	public String list(Model model) {
		List<Board> list = mapper.getLists();
		model.addAttribute("list",list);
		System.out.println("list = " + list);
		
		return "boardList";
	}
	@GetMapping("/boardForm.do")
	public String boardForm() {
		return "boardForm";
	}
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board vo) {
		mapper.boardInsert(vo);
		return "redirect:/boardList.do";
	}
	@GetMapping("/boardContent.do")
	public String boardContent(int idx, Model model) {
		Board vo =  mapper.boardContent(idx);
		
		vo.setContent(vo.getContent().replace("\n","<br/>"));
		
		mapper.boardCount(idx);
		model.addAttribute("vo",vo);
		
		return "boardContent";
	}
	@GetMapping("/boardDelete.do/{idx}")
	public String boardDelete(@PathVariable("idx") int idx) {
		mapper.boardDelete(idx);
		return "redirect:/boardList.do";
	}
	@GetMapping("/boardUpdateForm.do/{idx}")
	public String boardUpdateForm(@PathVariable("idx") int idx,Model model) {
		Board vo = mapper.boardContent(idx);
		model.addAttribute("vo",vo);
		return "boardUpdate";
	}
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(Board vo) {
		mapper.boardUpdate(vo);
		return "redirect:/boardList.do";
	}
	
}
