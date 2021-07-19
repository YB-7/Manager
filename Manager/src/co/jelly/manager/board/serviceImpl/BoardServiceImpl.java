package co.jelly.manager.board.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.jelly.manager.Dao;
import co.jelly.manager.board.service.BoardService;
import co.jelly.manager.board.vo.BoardVO;

public class BoardServiceImpl implements BoardService {
	private Connection conn = Dao.getConnction();
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<BoardVO> boardSelectList() {
		// TODO 게시판 목록
		List<BoardVO> list = new ArrayList<BoardVO>();
		String sql = "select * from board";
		BoardVO vo;

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVO();
				vo.setBoardid(rs.getString("boardid"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setEnterdate(rs.getDate("enterdate"));
				vo.setHit(rs.getInt("hit"));

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public BoardVO boardSelect(BoardVO vo) {
		// TODO 게시글 조회
		String sql = "select * from board where boardid = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getBoardid());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setSubject(rs.getString("subject"));
				vo.setEnterdate(rs.getDate("enterdate"));
				vo.setHit(rs.getInt("hit"));
				updateHit(vo.getBoardid()); // 조회수 증가시키는 메소드 호출
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	private void updateHit(String boardid) {
		// TODO 조회수 증가
		String sql = "update board set hit = hit + 1 where boardid = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, boardid);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int boardInsert(BoardVO vo) {
		int n = 0;
		String sql = "insert into board(boardid,writer,title,subject) values (?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getBoardid());
			psmt.setString(2, vo.getWriter());
			psmt.setString(3, vo.getTitle());
			psmt.setString(4, vo.getSubject());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int boardDelete(BoardVO vo) {
		// TODO 글 삭제
		String sql = "delete from board where boardid = ?";
		int n = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getBoardid());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int boardUpdate(BoardVO vo) {
		// TODO 글 수정
		int n = 0;
		String sql = "update board set subject = ? where boardid = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getSubject());
			psmt.setString(2, vo.getBoardid());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

}
