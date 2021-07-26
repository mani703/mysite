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

	public List<BoardVo> getBoardList(int currentPage, int pageSize) {
		int startRow = (currentPage - 1) * pageSize;
		
		return boardrepository.findAll(startRow, pageSize);
	}
	
	public List<BoardVo> searchToBoard(int currentPage, int pageSize, String kwd) {
		int startRow = (currentPage - 1) * pageSize;
		
		return boardrepository.findSearchAll(startRow, pageSize, kwd);
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
		boardrepository.addNumber(groupNo, orderNo);
	}

	public void deleteToBoard(Long no) {
		boardrepository.delete(no);
	}

	public Map<String, Integer> getMapInfo(int currentPage, int pageSize) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		int count = boardrepository.getPaging();
		int firstPage = 1;
		int lastPage = (int) Math.ceil((double) count / pageSize);

		int blockNum = (int) Math.floor((currentPage - 1) / pageSize);
		int blockStart = (pageSize * blockNum) + 1;
		int blockLast = blockStart + (pageSize - 1);

		map.put("currentPage", currentPage); // 현재 선택한 페이지
		map.put("firstPage", firstPage);
		map.put("lastPage", lastPage);
		map.put("blockStart", blockStart);
		map.put("blockLast", blockLast);
		
		return map;
	}

	public Map<String, Integer> getSearchMapInfo(int currentPage, int pageSize, String kwd) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		int count = boardrepository.getSearchPaging(kwd);
		int firstPage = 1;
		int lastPage = (int) Math.ceil((double) count / pageSize);

		int blockNum = (int) Math.floor((currentPage - 1) / pageSize);
		int blockStart = (pageSize * blockNum) + 1;
		int blockLast = blockStart + (pageSize - 1);

		map.put("currentPage", currentPage); // 현재 선택한 페이지
		map.put("firstPage", firstPage);
		map.put("lastPage", lastPage);
		map.put("blockStart", blockStart);
		map.put("blockLast", blockLast);

		return map;
	}

}
