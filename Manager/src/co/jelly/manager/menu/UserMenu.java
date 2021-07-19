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
		System.out.println("| 1.나의 정보 수정하기				|");
		System.out.println("| 2.게시글 보기					|");
		System.out.println("| 3.게시글 작성					|");
		System.out.println("| 4.게시글 수정					|");
		System.out.println("| 5.종     료					|");
		System.out.println("_______________________________________________");
		System.out.print("원하는 작업 선택>> ");
	}

	private void userMenu(MemberVO vo) { // 여기
		System.out.println(vo.getName() + "님 환영합니다.");
		boolean b = false;
		do {
			title();
			int choice;
			switch (choice = sc.nextInt()) {
			case 1:
				sc.nextLine();
				editMyInfo(vo);
				break;
			case 2:
				showBoard();
				break;
			case 3:
				sc.nextLine();
				writeBoard(vo);
				break;
			case 4:
				sc.nextLine();
				editBoard(vo.getId());
				break;
			case 5:
				b = true;
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		} while (!b);

	}

	private void editMyInfo(MemberVO vo) {
		// TODO 내 정보 수정
		int n = 0;
		System.out.print("수정할 전화번호 입력: ");
		vo.setTel(sc.nextLine());
		System.out.print("수정할 주소 입력: ");
		vo.setAddress(sc.nextLine());

		n = memDao.memberUpdate(vo);
		if (n != 0) {
			System.out.println("내 정보가 정상적으로 수정되었습니다.");
		} else {
			System.out.println("! 내 정보 수정 실패");
		}
	}

	private void showBoard() {
		// TODO 게시글 보기
		List<BoardVO> list = new ArrayList<BoardVO>();
		list = boDao.boardSelectList();

		for (BoardVO board : list) {
			board.toString();
		}
	}

	private void writeBoard(MemberVO vo) {
		// TODO 게시글 작성
		int n = 0;
		BoardVO board = new BoardVO();
		System.out.print("작성할 글의 ID 입력: ");
		board.setBoardid(sc.nextLine());
		board.setWriter(vo.getId());
		System.out.print("작성할 글의 제목 입력: ");
		board.setTitle(sc.nextLine());
		System.out.print("작성할 글의 내옹 입력: ");
		board.setSubject(sc.nextLine());
		n = boDao.boardInsert(board);

		if (n != 0) {
			System.out.println("글이 정상적으로 작성되었습니다.");
		} else {
			System.out.println("! 글 작성 실패");
		}
	}

	private void editBoard(String id) {
		// TODO 게시글 수정
		int n = 0;
		BoardVO board = new BoardVO();
		System.out.print("수정할 글의 ID 입력: ");
		String boardId = sc.nextLine();
		board.setBoardid(boardId);
		board = boDao.boardSelect(board);	//id에 맞는 글을 선택한다.
		if(board.getWriter().equals(id)) {
			board = new BoardVO();
			System.out.print("수정할 글의 내용 입력: ");
			board.setSubject(sc.nextLine());
			board.setWriter(id);
			board.setBoardid(boardId);
			n = boDao.boardUpdate(board);
			if(n != 0) {
				System.out.println("정상적으로 글이 수정되었습니다.");
			} else {
				System.out.println("! 글 수정 실패");
			}
		}
		else {
			System.out.println("본인의 글만 수정 가능합니다.");
			board.toString();
		}
	}

	public void run(MemberVO vo) {
		userMenu(vo);
	}
}