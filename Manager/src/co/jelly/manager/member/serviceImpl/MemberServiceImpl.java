package co.jelly.manager.member.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.jelly.manager.Dao;
import co.jelly.manager.member.service.MemberService;
import co.jelly.manager.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	private Connection conn = Dao.getConnction(); // DBMS와 연결하는 객체
	private PreparedStatement psmt; // conn을 통해서 sql 명령을 전달하고 실행하는 객체
	private ResultSet rs; // select구문을 호출시 결과를 돌려받는 객체

	@Override
	public List<MemberVO> memberSelectList() {
		// TODO 회원 전체 목록
		List<MemberVO> members = new ArrayList<MemberVO>();
		MemberVO member;
		String sql = "select * from member";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				member = new MemberVO();
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setAddress(rs.getString("address"));
				member.setTel(rs.getString("tel"));
				member.setAge(rs.getInt("age"));
				member.setAuthor(rs.getString("author"));

				members.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}

	@Override
	public MemberVO memberSelect(MemberVO vo) {
		String sql = "select * from member where id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
				vo.setAge(rs.getInt("age"));
				vo.setAuthor(rs.getString("author"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public MemberVO loginCheck(MemberVO vo) {
		// TODO 로그인 과정
		String sql = "select * from member where id = ? and password = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPassword());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setName(rs.getString("name"));
				vo.setAuthor(rs.getString("author"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public int memberInsert(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int memberDelete(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
