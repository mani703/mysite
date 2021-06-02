package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		BoardVo vo = new BoardVo();
		vo.setUserNo(Long.parseLong(no));
		vo.setTitle(title);
		vo.setContents(contents);
		
		System.out.println(title);
		System.out.println(contents);
		
		new BoardRepository().update(vo);
		MvcUtils.redirect(request.getContextPath() + "/board", request, response);
	}

}
