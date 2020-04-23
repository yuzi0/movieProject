package com.exam.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ColumnBoardDAO {
	private DataSource dataSource = null;

	public ColumnBoardDAO() {
		// TODO Auto-generated constructor stub
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			this.dataSource = (DataSource) envCtx.lookup("jdbc/mariadb");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] : " + e.getMessage());
		}
	}
	
	public BoardListTO columnList(BoardListTO lLTO) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;

		
		ArrayList<ColumnBoardTO> columnList=new ArrayList<ColumnBoardTO>();
		String opt = lLTO.getSearchKey();
		System.out.println("dao opt : " + opt);
		String searchText = lLTO.getSearchWord();
		System.out.println("dao searchText : " + searchText);
		try {
			conn = dataSource.getConnection();
			
			if(opt == null) {
				String sql = "select cnum, mlnum,csubject,unum,date_format(cdate, '%Y.%m.%d') cdate,chit,datediff(now(), cdate) cgap from column_board order by cnum desc";
				pstmt1 = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs1 = pstmt1.executeQuery();
			}else if(opt.equals("0")) {
				String sql = "select cnum, mlnum,csubject,unum,date_format(cdate, '%Y.%m.%d') cdate,chit,datediff(now(), cdate) cgap from column_board where csubject like ? order by cnum desc";
				pstmt1 = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				pstmt1.setString(1, "%" + searchText + "%");
				rs1 = pstmt1.executeQuery();
			}else if(opt.equals("1")) {
				String sql = "select cnum, mlnum,csubject,unum,date_format(cdate, '%Y.%m.%d') cdate,chit,datediff(now(), cdate) cgap from column_board where ccontent like ? order by cnum desc";
				pstmt1 = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				pstmt1.setString(1, "%" + searchText + "%");
				rs1 = pstmt1.executeQuery();
			}else if(opt.equals("none")) {
				String sql = "select cnum, mlnum,csubject,unum,date_format(cdate, '%Y.%m.%d') cdate,chit,datediff(now(), cdate) cgap from column_board order by cnum desc";
				pstmt1 = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs1 = pstmt1.executeQuery();
			}


			rs1.last();
			lLTO.setTotalRecord(rs1.getRow());
			rs1.beforeFirst();
			
			lLTO.setTotalPage((lLTO.getTotalRecord() - 1) / lLTO.getRecordPerPage() + 1);
			
			int skip = (lLTO.getCpage() - 1) * lLTO.getRecordPerPage();
			if (skip != 0)
				rs1.absolute(skip);
			
			for (int i=0; i<lLTO.getRecordPerPage() && rs1.next();i++) {
				ColumnBoardTO cTO=new ColumnBoardTO();
				String sql="select mlsubject from movie_list where mlnum=?";
				pstmt2=conn.prepareStatement(sql);
				pstmt2.setString(1, rs1.getString("mlnum"));
				rs2=pstmt2.executeQuery();
				
				if(rs2.next()) {
					cTO.setCnum(rs1.getInt("cnum"));
					cTO.setMsubject(rs2.getString("mlsubject"));
					cTO.setCsubject(rs1.getString("csubject"));
					//lTO.setUid(rs3.getString("uid"));
					cTO.setCdate(rs1.getString("cdate"));
					cTO.setChit(rs1.getInt("chit"));
					cTO.setCgap(rs1.getInt("cgap"));
					
				}
				columnList.add(cTO);

			}
			
			lLTO.setColumnList(columnList);
		lLTO.setStartBlock(((lLTO.getCpage() - 1) / lLTO.getBlockPerPage()) * lLTO.getBlockPerPage() + 1);
			lLTO.setEndBlock(((lLTO.getCpage() - 1) / lLTO.getBlockPerPage()) * lLTO.getBlockPerPage() + lLTO.getBlockPerPage());
			System.out.println("start : "+lLTO.getStartBlock());
			if (lLTO.getEndBlock() >= lLTO.getTotalPage()) {
				lLTO.setEndBlock(lLTO.getTotalPage());
			}
			System.out.println("end: "+lLTO.getEndBlock());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs1 != null)
				try {
					rs1.close();
				} catch (SQLException e) {
				}
			if (rs2 != null)
				try {
					rs2.close();
				} catch (SQLException e) {
				}
			if (rs3 != null)
				try {
					rs3.close();
				} catch (SQLException e) {
				}
			if (pstmt1 != null)
				try {
					pstmt1.close();
				} catch (SQLException e) {
				}
			if (pstmt2 != null)
				try {
					pstmt1.close();
				} catch (SQLException e) {
				}
			if (pstmt3 != null)
				try {
					pstmt1.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}

			
		}
		return lLTO;
	}
	
	public int columnWrite(ColumnBoardTO cTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		int flag = 1;
		int cnum=0;

		try {
			conn = dataSource.getConnection();
			String sql = "select mlnum from movie_list where mlsubject=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cTO.getMsubject());

			rs1 = pstmt.executeQuery();

			sql = "select unum from member where uid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cTO.getUid());
			rs2 = pstmt.executeQuery();

			if (rs1.next() && rs2.next()) {
				sql = "insert into column_board values(0,?,?,0,now(),?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cTO.getCsubject());
				pstmt.setString(2, cTO.getCcontent());
				pstmt.setString(3, cTO.getCip());
				pstmt.setString(4, rs2.getString("unum"));
				pstmt.setString(5, rs1.getString("mlnum"));
			}

			int result = pstmt.executeUpdate();
			if (result == 1) {
				sql="select cnum from column_board order by cnum desc limit 1";
				pstmt = conn.prepareStatement(sql);
				rs1 = pstmt.executeQuery();
				if(rs1.next()) cnum=rs1.getInt("cnum");
				
				
				//flag = 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] " + e.getMessage());
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
			if (rs1 != null)
				try {
					rs1.close();
				} catch (SQLException e) {
				}
			if (rs2 != null)
				try {
					rs2.close();
				} catch (SQLException e) {
				}
		}

		return cnum;
	}
	
	public ColumnBoardTO columnView(ColumnBoardTO cTO,String currentUser) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs4 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs5 = null;
		try {
			conn = dataSource.getConnection();
			
			String sql = "update column_board set chit=chit+1 where cnum=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setInt( 1, cTO.getCnum() );
			pstmt.executeUpdate();
			
			sql="select mlnum,csubject,unum,date_format(cdate, '%Y.%m.%d') cdate, chit,ccontent from column_board where cnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cTO.getCnum());
			rs1=pstmt.executeQuery();
			
			if(rs1.next()) {
				sql="select mlsubject from movie_list where mlnum=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, rs1.getString("mlnum"));
				rs2=pstmt.executeQuery();

				sql="select uid from member where unum=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, rs1.getString("unum"));
				rs3=pstmt.executeQuery();
				
				sql="select count(*) from column_comment where cnum=? group by cnum";
				pstmt2=conn.prepareStatement(sql);
				pstmt2.setInt(1, cTO.getCnum());
				rs4=pstmt2.executeQuery();
				
				sql="select anum from admin where aid=?";
				pstmt3 = conn.prepareStatement(sql);
				pstmt3.setString(1, currentUser);
				rs5 = pstmt3.executeQuery();
				
				if(rs5.next()) {
					cTO.setAnum(rs5.getInt("anum"));
					
				}
				if(rs3.next()) {
					cTO.setUid(rs3.getString("uid"));
				}
				if(rs2.next()) {
					if(rs4.next()) {
						cTO.setCommentNum(rs4.getInt("count(*)"));
					}
					cTO.setMsubject(rs2.getString("mlsubject"));
					cTO.setCsubject(rs1.getString("csubject"));
					cTO.setCdate(rs1.getString("cdate"));
					cTO.setChit(rs1.getInt("chit"));
					cTO.setCcontent(rs1.getString("ccontent"));
					
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (rs1 != null)
				try {
					rs1.close();
				} catch (SQLException e) {
				}
			if (rs2 != null)
				try {
					rs2.close();
				} catch (SQLException e) {
				}
			if (rs3 != null)
				try {
					rs3.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}

			
		}
		return cTO;
	}
	
	public ColumnBoardTO columnModify(ColumnBoardTO cTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			conn = dataSource.getConnection();
			String sql="select csubject,mlnum,ccontent from column_board where cnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cTO.getCnum());
			rs1=pstmt.executeQuery();
			
			if(rs1.next()) {
				sql="select mlsubject from movie_list where mlnum=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, rs1.getString("mlnum"));
				rs2=pstmt.executeQuery();
				
				if(rs2.next()) {
					cTO.setMsubject(rs2.getString("mlsubject"));
					cTO.setCsubject(rs1.getString("csubject"));
					cTO.setCcontent(rs1.getString("ccontent"));
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (rs1 != null)
				try {
					rs1.close();
				} catch (SQLException e) {
				}
			if (rs2 != null)
				try {
					rs2.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}

			
		}
		
		return cTO;
	}
	
	public int coulmnModifyOk(ColumnBoardTO cTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int flag = 2;
		
		try {
			conn = dataSource.getConnection();

			String sql="select mlnum from movie_list where mlsubject=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, cTO.getMsubject());
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				sql = "update column_board set mlnum=?,csubject=?,ccontent=?, cip=? where cnum=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, rs.getString("mlnum"));
				pstmt.setString(2, cTO.getCsubject());
				pstmt.setString(3, cTO.getCcontent());
				pstmt.setString(4, cTO.getCip());
				pstmt.setInt(5, cTO.getCnum());
			}
			
			int result=pstmt.executeUpdate();
			
			if(result==0) {
				flag=1;
			}
			else if(result==1) {
				flag=0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		
		return flag;
	}
	
	public int columnDelete(ColumnBoardTO cTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag=2;
		
		try {
			conn = dataSource.getConnection();

			String sql="delete from column_board where cnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cTO.getCnum());
			
			int result=pstmt.executeUpdate();
			
			if(result==0) flag=1;
			else if(result==1) flag=0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		
		}
		
		return flag;
	}

	public BoardListTO mainColumn() {
		BoardListTO lLTO = new BoardListTO();
		ArrayList<ColumnBoardTO> columnList=new ArrayList<ColumnBoardTO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			
			String sql = "select cnum, csubject, ccontent, date_format(cdate, '%Y.%m.%d') cdate, chit from column_board order by chit desc limit 3";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ColumnBoardTO cTO=new ColumnBoardTO();
				
				cTO.setCnum(rs.getInt("cnum"));
				cTO.setCsubject(rs.getString("csubject"));
				cTO.setCdate(rs.getString("cdate"));
				cTO.setChit(rs.getInt("chit"));

				String content = rs.getString("ccontent");
				String content_before = content.substring(0, content.indexOf("<img"));
				String content_after = content.substring(content.indexOf("<img"));
				String cimg = content_after.substring(content_after.indexOf("src=\"") + 5);
				cimg = cimg.substring(0, cimg.indexOf("\""));
				content = content_before + content_after.substring(content_after.indexOf("/>") + 2);
				content = content.replace("<p>", "");
				content = content.replace("</p>", "");
				content = content.replace("\n", "");
				cTO.setCcontent(content);
				cTO.setCimg(cimg);
				columnList.add(cTO);

				System.out.println("content : "  + content);
				System.out.println("cimg : " + cimg);
			}
			lLTO.setColumnList(columnList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {}
			if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
			if (conn != null) try { conn.close(); } catch (SQLException e) {}
		}
		return lLTO;
	}
}
