package co.jelly.manager.member.service;

import java.util.List;

import co.jelly.manager.member.vo.MemberVO;

public interface MemberService {
	List<MemberVO> memberSelectList(); // 전체 회원 목록

	MemberVO memberSelect(MemberVO vo); // 회원조회

	MemberVO loginCheck(MemberVO vo); // 로그인처리

	int memberInsert(MemberVO vo); // 회원추가

	int memberDelete(MemberVO vo); // 회원삭제

	int memberUpdate(MemberVO vo); // 회원수정

}
