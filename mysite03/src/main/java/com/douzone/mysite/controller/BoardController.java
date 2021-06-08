package com.douzone.mysite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardservice;

	@RequestMapping("")
	public String index(Model model) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		int currentPage = 1;
		
		int pageSize = 5;
		int count = boardservice.getPaging();
		int firstPage = 1;
		int lastPage = (int) Math.ceil((double) count / pageSize);
		int startRow = (currentPage - 1) * pageSize;
		int endRow = currentPage * pageSize;

		int blockNum = (int) Math.floor((currentPage - 1) / pageSize);
		int blockStart = (pageSize * blockNum) + 1;
		int blockLast = blockStart + (pageSize - 1);

		map.put("count", count); // 게시물 총 개수
		map.put("pageSize", pageSize); // 한 페이지당 나오는 개수
		map.put("currentPage", currentPage); // 현재 선택한 페이지
		map.put("startRow", startRow); // 한 페이지의 시작글
		map.put("endRow", endRow); // 한 페이지의 마시작
		map.put("firstPage", firstPage);
		map.put("lastPage", lastPage);
		map.put("blockStart", blockStart);
		map.put("blockLast", blockLast);
		
		List<BoardVo> list = boardservice.getBoardList(startRow, pageSize);
		
		model.addAttribute("map", map);
		model.addAttribute("list", list);
		return "board/index";
	}
	
	@RequestMapping(value="/p/{page}", method=RequestMethod.GET)
	public String index(@PathVariable("page") int page, Model model) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		int currentPage = page;
		
		int pageSize = 5;
		int count = boardservice.getPaging();
		int firstPage = 1;
		int lastPage = (int) Math.ceil((double) count / pageSize);
		int startRow = (currentPage - 1) * pageSize;
		int endRow = currentPage * pageSize;

		int blockNum = (int) Math.floor((currentPage - 1) / pageSize);
		int blockStart = (pageSize * blockNum) + 1;
		int blockLast = blockStart + (pageSize - 1);

		map.put("count", count); // 게시물 총 개수
		map.put("pageSize", pageSize); // 한 페이지당 나오는 개수
		map.put("currentPage", currentPage); // 현재 선택한 페이지
		map.put("startRow", startRow); // 한 페이지의 시작글
		map.put("endRow", endRow); // 한 페이지의 마시작
		map.put("firstPage", firstPage);
		map.put("lastPage", lastPage);
		map.put("blockStart", blockStart);
		map.put("blockLast", blockLast);
		
		List<BoardVo> list = boardservice.getBoardList(startRow, pageSize);
		
		model.addAttribute("map", map);
		model.addAttribute("list", list);
		return "board/index";
	}
	
	@RequestMapping("/writeform")
	public String writeForm() {
		return "board/write";
	}
	
	@RequestMapping(value="/write/{authNo}", method=RequestMethod.POST)
	public String write(@PathVariable("authNo") Long authNo, BoardVo vo) {
		vo.setUserNo(authNo);
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
	
	@RequestMapping(value="/reply/{no}/{authNo}", method=RequestMethod.POST)
	public String insert(@PathVariable("no") Long no,
						 @PathVariable("authNo") Long authNo,
						 @RequestParam(value="title", required = true, defaultValue = "") String title,
						 @RequestParam(value="contents", required = true, defaultValue = "") String contents) {
		BoardVo vo = boardservice.getInfoBoardCount(no);
		vo.setUserNo(authNo);
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
