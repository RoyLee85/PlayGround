package com.pg.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

import com.pg.VO.MembersVO;
import com.sun.org.apache.xml.internal.utils.NameSpace;

@Component
public class MembersDaoImpl extends SqlSessionDaoSupport implements MembersDao {
	
	private final static String NAMESPACE = "MembersDao";
	
	public int join(MembersVO vo){
		return getSqlSession().insert(NAMESPACE + ".join",vo);
	}
	
	public MembersVO getMember(String id){
		return getSqlSession().selectOne(NAMESPACE + ".getMember", id);
	}
	
}
