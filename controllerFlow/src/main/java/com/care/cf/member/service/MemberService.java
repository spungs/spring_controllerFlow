package com.care.cf.member.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.cf.member.dto.MemberDTO;
import com.care.cf.member.repository.MemberDAO;

@Service
public class MemberService {
	@Autowired private MemberDAO memberDao;
	
	public String register(MemberDTO member) {
		if(member.getId().isEmpty() || member.getPw().isEmpty()) {
			return "필수 정보를 입력하세요.";
		}
		
		memberDao.register(member);
		return "회원 가입 성공";
	}
	
	public ArrayList<MemberDTO> list(){
		return memberDao.list();
		
	}
}
