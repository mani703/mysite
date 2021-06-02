package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long userNo = Long.parseLong(request.getParameter("userNo"));
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		int hit = Integer.parseInt(request.getParameter("hit"));
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		int orderNo = Integer.parseInt(request.getParameter("orderNo"));
		int depth = Integer.parseInt(request.getParameter("depth"));
	
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setHit(hit);
		vo.setGroupNo(groupNo);
		vo.setOrderNo(orderNo);
		vo.setDepth(depth);
		vo.setUserNo(userNo);
		
		new BoardRepository().addNumber(groupNo ,orderNo);
		new BoardRepository().reply(vo);
		MvcUtils.redirect(request.getContextPath() + "/board", request, response);
	}

}
