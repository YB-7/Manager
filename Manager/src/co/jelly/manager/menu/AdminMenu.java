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

public class AdminMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberService memDao = new MemberServiceImpl(); // 멤버
	private BoardService boDao = new BoardServiceImpl(); // 게시글

	private void title() {
		System.out.println("_______________________________________________");
		System.out.println("		< 관리자 메뉴 >");
		System.out.println("| 1.사용자 관리					|");
		System.out.println("| 2.게시판 관리					|");
		System.out.println("| 3.종    료 					|");
		System.out.println("_______________________________________________");
		System.out.print("원하는 작업 선택>> ");
	}

	private void subMemberTitle() {
		System.out.println("_______________________________________________");
		System.out.println("		< 사용자 관리 >");
		System.out.println("| 1.멤버 목록 보기					|");
		System.out.println("| 2.멤버 정보 조회					|");
		System.out.println("| 3.멤버 정보 삭제					|");
		System.out.println("| 4.돌아가기					|");
		System.out.println("_______________________________________________");
		System.out.print("원하는 작업 선택>> ");
	}

	private void subBoardTitle() {
		System.out.println("_______________________________________________");
		System.out.println("		< 게시판 관리 >");
		System.out.println("| 1.글 목록 보기					|");
		System.out.println("| 2.글 쓰기					|");
		System.out.println("| 3.글 수정					|");
		System.out.println("| 4.글 삭제					|");
		System.out.println("| 5.돌아가기					|");
		System.out.println("_______________________________________________");
		System.out.print("원하는 작업 선택>> ");
	}

	private void adminMenu(MemberVO vo) { // 여기
		System.out.println(vo.getName() + " 관리자님 환영합니다.");
		boolean b = false;
		do {
			title(); // 주메뉴 출력
			int choice;
			switch (choice = sc.nextInt()) {
			case 1:
				userManager();
				break;
			case 2:
				boardManager();
				break;
			case 3:
				b = true;
				System.out.println("작업을 종료합니다.");
				break;
			}
		} while (!b);
	}

	private void boardManager() {
		// TODO 게시판 관리
		boolean b = false;
		do {
			int chk;
			subBoardTitle();
			switch (chk = sc.nextInt()) {
			case 1:
				boardSelectList(); // 글 목록
				break;
			case 2:
				sc.nextLine();
				boardInsert(); // 글 쓰기
				break;
			case 3:
				sc.nextLine();
				boardUpdate(); // 글 수정
				break;
			case 4:
				sc.nextLine();
				boardDelete(); // 글 삭제
				break;
			case 5:
				b = true;
				System.out.println("목록으로 돌아갑니다.");
				break;
			}

		} while (!b);
	}

	private void boardSelectList() {
		// TODO 글 목록 조회
		List<BoardVO> boards = new ArrayList<BoardVO>();
		boards = boDao.boardSelectList();
		for (BoardVO board : boards) {
			board.toString();
		}
	}

	private void boardInsert() {
		// TODO 글 쓰기
		BoardVO vo = new BoardVO();
		System.out.print("게시판 ID 입력: ");
		vo.setBoardid(sc.next());
		sc.nextLine();
		System.out.print("게시판 제목 입력: ");
		vo.setTitle(sc.nextLine());
		System.out.print("게시판 내용 입력: ");
		vo.setSubject(sc.nextLine());
		System.out.print("작성자 입력: ");
		vo.setWriter(sc.next());
		sc.nextLine();

		int n = boDao.boardInsert(vo);

		if (n != 0) {
			System.out.println("정상적으로 입력되었습니다.");
		} else {
			System.out.println("! 입력 실패");
		}
	}

	private void boardUpdate() {
		// TODO 글 수정(내용만)
		int n = 0;
		BoardVO vo = new BoardVO();
		System.out.print("수정할 게시글의 ID 입력: ");
		vo.setBoardid(sc.nextLine());
		System.out.print("수정할 내용 입력: ");
		vo.setSubject(sc.nextLine());

		n = boDao.boardUpdate(vo);

		if (n != 0) {
			System.out.println("정상적으로 수정되었습니다.");
		} else {
			System.out.println("! 수정 실패");
		}

	}

	private void boardDelete() {
		// TODO 글 삭제
		int n = 0;
		BoardVO vo = new BoardVO();
		System.out.print("삭제할 게시글의 ID 입력: ");
		vo.setBoardid(sc.nextLine());

		n = boDao.boardDelete(vo);

		if (n != 0) {
			System.out.println("정상적으로 삭제되었습니다.");
		} else {
			System.out.println("! 삭제 실패");
		}

	}

	private void userManager() {
		// TODO 사용자 관리
		boolean b = false;
		do {
			int chk;
			subMemberTitle(); // 사용자 메뉴 출력
			switch (chk = sc.nextInt()) {
			case 1:
				memberSelectList(); // 멤버목록 조회
				break;
			case 2:
				sc.nextLine();
				memberSelect(); // 멤버 조회
				break;
			case 3:
				sc.nextLine();
				memberDelete(); // 멤버 삭제
				break;
			case 4:
				b = true;
				System.out.println("목록으로 돌아갑니다.");
				break;
			}
		} while (!b);
	}

	private void memberDelete() {
		// TODO 멤버 삭제
		MemberVO member = new MemberVO();
		System.out.print("삭제할 ID를 입력>>> ");
		member.setId(sc.nextLine());
		int n = memDao.memberDelete(member);
		if (n != 0) {
			System.out.println("정상적으로 삭제되었습니다.");
		} else {
			System.out.println("! 삭제 실패");
		}

	}

	private void memberSelect() {
		// TODO 멤버 조회
		MemberVO member = new MemberVO();
		System.out.print("검색할 ID를 입력>>> ");
		member.setId(sc.nextLine());
		member = memDao.memberSelect(member);

		member.toString();
	}

	private void memberSelectList() {
		// TODO 멤버 목록 조회
		System.out.println("		- 게시판 관리 -");

		List<MemberVO> members = new ArrayList<MemberVO>();
		members = memDao.memberSelectList();
		for (MemberVO member : members) {
			member.toString();
		}
	}

	public void run(MemberVO vo) {
		adminMenu(vo);
	}
}
