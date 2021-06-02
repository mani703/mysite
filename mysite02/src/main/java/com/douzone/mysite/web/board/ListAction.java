package com.douzone.mysite.web.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		int currentPage = 1;
		if(request.getParameter("p") != null) {
			currentPage = Integer.parseInt(request.getParameter("p"));
		} 
		
		int pageSize = 5;
		int count = new BoardRepository().getPaging();
		int firstPage = 1;
		int lastPage = (int)Math.ceil((double)count/pageSize);
		int startRow = (currentPage-1) * pageSize;
		int endRow = currentPage * pageSize;
		
		int blockNum = (int)Math.floor((currentPage-1)/pageSize);
		int blockStart = (pageSize * blockNum) + 1;
		int blockLast = blockStart + (pageSize-1);
		
		List<BoardVo> list = new BoardRepository().findAll(pageSize, startRow);
		for (BoardVo boardVo : list) {
			String regDate = boardVo.getRegDate();
			boardVo.setRegDate(regDate.substring(0, 19));
		}
		
		map.put("count", count);	// 게시물 총 개수
		map.put("pageSize", pageSize);	// 한 페이지당 나오는 개수
		map.put("currentPage", currentPage);	// 현재 선택한 페이지
		map.put("startRow", startRow);	// 한 페이지의 시작글
		map.put("endRow",endRow);	// 한 페이지의 마시작
		map.put("firstPage", firstPage);
		map.put("lastPage", lastPage);
		map.put("blockStart", blockStart);
		map.put("blockLast", blockLast);
		
		request.setAttribute("map", map);
		request.setAttribute("list", list);
		MvcUtils.forward("board/list", request, response);

	}

}