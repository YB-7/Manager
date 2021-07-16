package co.jelly.manager.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.jelly.manager.board.service.BoardService;
import co.jelly.manager.board.serviceImpl.BoardServiceImpl;
import co.jelly.manager.board.vo.BoardVO;
import co.jelly.manager.member.service.MemberService;
import co.jelly.manager.member.serviceImpl.MemberServiceImpl;
import co.jelly.manager.member.vo.MemberVO;

public class UserMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberService memDao = new MemberServiceImpl(); // 멤버 정보 수정
	private BoardService boDao = new BoardServiceImpl(); // 게시글

	private void title() {
		System.out.println("_______________________________________________");
		System.out.println("		< 사용자 메뉴 >");
	}

	private void userMenu(MemberVO vo) {
		System.out.println(vo.getName() + "님 환영합니다.");
		title();
		boardList();
	}

	private void boardList() {
		// TODO Auto-generated method stub
		List<BoardVO> list = new ArrayList<BoardVO>();
		list = boDao.boardSelectList();

		for (BoardVO vo : list) {
			vo.toString();
		}
	}

	public void run(MemberVO vo) {
		userMenu(vo);
	}
}