package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String userNo = request.getParameter("userNo");
		String name = request.getParameter("name");
		
		request.setAttribute("no", no);
		request.setAttribute("title", title);
		request.setAttribute("contents", contents);
		request.setAttribute("userNo", userNo);
		request.setAttribute("name", name);
		
		new BoardRepository().upHit(Long.parseLong(no));
		
		MvcUtils.forward("board/view", request, response);
	}

}