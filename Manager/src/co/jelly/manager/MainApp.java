package co.jelly.manager;

import java.util.ArrayList;
import java.util.List;

import co.jelly.manager.member.service.MemberService;
import co.jelly.manager.member.serviceImpl.MemberServiceImpl;
import co.jelly.manager.member.vo.MemberVO;
import co.jelly.manager.menu.LoginMenu;

public class MainApp {
	public static void main(String[] args) {
//		MemberService memberDao = new MemberServiceImpl();
//		List<MemberVO> members = new ArrayList<MemberVO>();
//		
//		members = memberDao.memberSelectList();
//		for(MemberVO vo : members) {
//			vo.toString();
//		}
		LoginMenu menu = new LoginMenu();
		menu.login();
	}
}