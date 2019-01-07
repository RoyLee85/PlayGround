package com.pg.dao;

import com.pg.VO.BoardVO;
import com.pg.dao.BoardDao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

@Component
public class BoardDaoImpl extends SqlSessionDaoSupport implements BoardDao {
	
	private final static String NAMESPACE = "BoardDao";
	
	public List<BoardVO> list(Map<String, Object> map) {
		List<BoardVO> list = getSqlSession().selectList(NAMESPACE+".boardList", map);
		return list;
	}

	public int getCount(Map<String, Object> map) {
		return ((Integer) getSqlSession().selectOne(NAMESPACE+".boardCount", map)).intValue();
	}
	
	public int insert(BoardVO vo){
		return getSqlSession().insert(NAMESPACE+".boardInsert",vo);
	}
	
	public BoardVO getInfo(int seq){
		return getSqlSession().selectOne(NAMESPACE+".getInfo",seq);
	}
	
	public int addHit(int seq){
		return getSqlSession().update(NAMESPACE+".addHit",seq);
	}
	
	public int delete(int seq){
		return getSqlSession().delete(NAMESPACE+".boardDelete",seq);
	}
	
	public int getMaxSeq(){
		return getSqlSession().selectOne(NAMESPACE+".getMaxSeq");
	}
	
	public int updateFilename(BoardVO vo){
		return getSqlSession().update(NAMESPACE+".updateFilename",vo);
	}
	
	public int updateOk(BoardVO vo){
		return getSqlSession().update(NAMESPACE+".updateOk",vo);
	}
	
	public int stepUp(BoardVO vo){
		return getSqlSession().update(NAMESPACE+".stepUp",vo);
	}
}
