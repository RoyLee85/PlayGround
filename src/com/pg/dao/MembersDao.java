package com.pg.dao;

import com.pg.VO.MembersVO;

public abstract interface MembersDao {

	public abstract int join(MembersVO vo);
	
	public abstract MembersVO getMember(String id);
}
