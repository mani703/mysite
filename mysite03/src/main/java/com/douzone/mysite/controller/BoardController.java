package com.douzone.mysite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardservice;
	
	@RequestMapping(value={"", "/{page}"})
	public String index(@PathVariable Optional<Integer> page, Model model) {
		int currentPage = page.isPresent() ? page.get() : 1;
		int pageSize = 5;
		
		Map<String, Integer> map = boardservice.getMapInfo(currentPage, pageSize);
		List<BoardVo> list = boardservice.getBoardList(currentPage, pageSize);
		
		model.addAttribute("map", map);
		model.addAttribute("list", list);
		return "board/index";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String search(@RequestParam(value="kwd", required=true, defaultValue="") String kwd,
						@RequestParam(value="page", required=true, defaultValue="1") int page,
						Model model) {
		
		int currentPage = page;
		int pageSize = 5;
		
		Map<String, Integer> map = boardservice.getSearchMapInfo(currentPage, pageSize);
		List<BoardVo> list = boardservice.searchToBoard("kwd");
		model.addAttribute("list", list);
		return "board/index";
	}
	
	@RequestMapping("/writeform")
	public String writeForm() {
		return "board/write";
	}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@AuthUser UserVo authUser, BoardVo vo) {
		vo.setUserNo(authUser.getNo());
		boardservice.insertToBoard(vo);
		return "redirect:/board";
	} 
	
	@RequestMapping(value="/view/{no}", method=RequestMethod.GET)
	public String view(Model model, @PathVariable("no") Long no) {
		BoardVo vo = boardservice.getBoardRow(no);
		model.addAttribute("vo", vo);
		boardservice.countUpHit(no);
		
		return "board/view";
	}
	
	@RequestMapping(value="/modifyform/{no}", method=RequestMethod.GET)
	public String modifyForm(@PathVariable("no") Long no, Model model) {
		BoardVo vo = boardservice.getInfoMessage(no);
		model.addAttribute("vo", vo);
		
		return "board/modify";
	}
	
	@RequestMapping(value="/update/{no}", method=RequestMethod.POST)
	public String update(@PathVariable("no") Long no, BoardVo vo) {
		vo.setUserNo(no);
		boardservice.updateToBoard(vo);
		return "redirect:/board";
	}
	
	@RequestMapping(value="/replyform/{no}", method=RequestMethod.GET)
	public String replyForm(@PathVariable("no") Long no, Model model) {
		model.addAttribute(no);
		return "board/reply";
	}
	
	@Auth
	@RequestMapping(value="/reply/{no}", method=RequestMethod.POST)
	public String insert(@PathVariable("no") Long no,
						 @AuthUser UserVo authUser,
						 @RequestParam(value="title", required = true, defaultValue = "") String title,
						 @RequestParam(value="contents", required = true, defaultValue = "") String contents) {
		BoardVo vo = boardservice.getInfoBoardCount(no);
		vo.setUserNo(authUser.getNo());
		vo.setTitle(title);
		vo.setContents(contents);
		boardservice.updateInfoNumber(vo.getGroupNo(), vo.getOrderNo());
		boardservice.replyToBoard(vo);
		return "redirect:/board";	
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable("no") Long no) {
		 boardservice.deleteToBoard(no);
		 return "redirect:/board";
	}
}
