package com.myspring.basic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myspring.basic.entity.Member;

@Mapper
public interface MemberMapper {
	public List<Member> getList();
	public void memberInsert(Member vo);
	public Member checkMember(Member vo);
}
