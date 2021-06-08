package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardrepository;

	public List<BoardVo> getBoardList(int startRow, int pageSize) {
		Map<String, Integer> pageInfo = new HashMap<>();
		pageInfo.put("startRow", startRow);
		pageInfo.put("pageSize", pageSize);
		return boardrepository.findAll(pageInfo);
	}

	public int getPaging() {
		return boardrepository.getPaging();
	}

	public void insertToBoard(BoardVo vo) {
		boardrepository.insert(vo);
	}

	public void updateToBoard(BoardVo vo) {
		boardrepository.update(vo);
	}

	public void replyToBoard(BoardVo vo) {
		boardrepository.reply(vo);
	}

	public void countUpHit(Long no) {
		boardrepository.upHit(no);
	}

	public BoardVo getBoardRow(Long no) {
		return boardrepository.getRow(no);
	}

	public BoardVo getInfoMessage(Long no) {
		return boardrepository.getMessage(no);
	}

	public BoardVo getReplyMessage(Long no) {
		return null;
	}

	public BoardVo getInfoBoardCount(Long no) {
		return boardrepository.getInfoCount(no);
	}

	public void updateInfoNumber(int groupNo, int orderNo) {
		Map<String, Integer> pageInfo = new HashMap<>();
		pageInfo.put("groupNo", groupNo);
		pageInfo.put("orderNo", orderNo);
		boardrepository.addNumber(pageInfo);
	}

	public void deleteToBoard(Long no) {
		boardrepository.delete(no);
	}
}
