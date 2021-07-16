package co.jelly.manager.menu;

import java.util.Scanner;

import co.jelly.manager.member.service.MemberService;
import co.jelly.manager.member.serviceImpl.MemberServiceImpl;
import co.jelly.manager.member.vo.MemberVO;

public class LoginMenu {
	private MemberService memberDao = new MemberServiceImpl();
	private Scanner sc = new Scanner(System.in);

	// 사용자 메뉴객체 생성
	private UserMenu user = new UserMenu();
	// 관리자 메뉴객체 생성
	private AdminMenu admin = new AdminMenu();

	private void loginTitle() {
		System.out.println("_______________________________________________");
		System.out.println("		< 로 그 인 >");
		System.out.println("_______________________________________________");
	}

	private MemberVO loginCheck() {
		MemberVO vo = new MemberVO();
		boolean b = false;
		int loginCount = 1;
		do {
			loginTitle(); // 타이틀 출력
			System.out.println("아이디를 입력하세요");
			vo.setId(sc.next());
			sc.nextLine();
			System.out.println("패스워드를 입력하세요");
			vo.setPassword(sc.next());
			sc.nextLine();

			vo = memberDao.loginCheck(vo);
			if (vo.getName() != null) {
				b = true;
			} else {
				System.out.println("존재하지 않는 아이디 또는 패스워드가 틀렸습니다.");
				if (loginCount == 3) {
					b = true;
					System.out.println("로그인시도 " + loginCount + "회 초과 관리자에게 문의하세요.");
					return new MemberVO();
				} else {
					loginCount++;
				}
			}
		} while (!b);

		return vo;
	}

	public void login() {
		MemberVO vo = new MemberVO();
		vo = loginCheck();
		if (vo.getId() != null) {
			if (vo.getAuthor().equals("ADMIN")) {
				admin.run(vo);
			} else {
				user.run(vo);
			}
		}
		sc.close(); // 여기서 한번에 닫아줌
	}
}
