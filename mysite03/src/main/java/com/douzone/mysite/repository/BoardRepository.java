package com.douzone.mysite.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> findAll(Map<String, Integer> pageInfo) {
		return sqlSession.selectList("board.findAll", pageInfo);
	}
	
	public boolean addNumber(Map<String, Integer> pageInfo) {
		int count = sqlSession.update("board.addNumber", pageInfo);
		return count == 1;
	}

	public boolean insert(BoardVo vo) {
		int count = sqlSession.insert("board.insert", vo);
		return count == 1;
	}

	public boolean update(BoardVo vo) {
		int count = sqlSession.update("board.update", vo);
		return count == 1;
	}
	
	public boolean reply(BoardVo vo) {
		int count = sqlSession.insert("board.reply", vo);
		return count == 1;
	}

	public BoardVo getInfoCount(Long no) {
		return sqlSession.selectOne("board.getInfoCount", no);
	}
	
	public boolean upHit(Long no) {
		int count = sqlSession.update("board.upHit", no);
		return count == 1;
	}
	
	public int getPaging() {
		return sqlSession.selectOne("board.getPaging");
	}
	
	public BoardVo getRow(Long no) {
		return sqlSession.selectOne("board.getRow", no);
	}
	
	public BoardVo getMessage(Long no) {
		return sqlSession.selectOne("board.getMessage", no);
	}

	public boolean delete(Long no) {
		int count = sqlSession.delete("board.delete", no);
		return count == 1;
	}

}
