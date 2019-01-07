package com.pg.dao;

import com.pg.VO.BoardVO;

import java.util.List;
import java.util.Map;

public abstract interface BoardDao {
	
	public abstract List<BoardVO> list(Map<String, Object> paramMap);

	public abstract int getCount(Map<String, Object> paramMap);

	public abstract int insert(BoardVO vo);
	
	public abstract BoardVO getInfo(int seq);
	
	public abstract int addHit(int seq);

	public abstract int delete(int seq);
	
	public abstract int getMaxSeq();
	
	public abstract int updateFilename(BoardVO vo);
	
	public abstract int updateOk(BoardVO vo);
	
	public abstract int stepUp(BoardVO vo);
}
