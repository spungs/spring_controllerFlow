package com.care.cf.member.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.care.cf.member.dto.MemberDTO;

@Repository
public class MemberDAO {
	private ArrayList<MemberDTO> list;
	 
	public MemberDAO() {
		list = new ArrayList<>();
	}
	
	public void register(MemberDTO member) {
		list.add(member);
	}
	
	public ArrayList<MemberDTO> list () {
		return list;
	}
}
