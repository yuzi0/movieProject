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

public class FreeBoardDAO {
	private DataSource dataSource = null;

	public FreeBoardDAO() {
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

	public BoardListTO freeList(BoardListTO lLTO) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs1 = null;

		ArrayList<FreeBoardTO> freeList = new ArrayList<FreeBoardTO>();
		String opt = lLTO.getSearchKey();
		System.out.println("dao opt : " + opt);
		String searchText = lLTO.getSearchWord();
		System.out.println("dao searchText : " + searchText);
		try {
			conn = dataSource.getConnection();
			
			if(opt == null) {
				String sql = "select fnum, fsubject,date_format(fdate, '%Y.%m.%d') fdate,fhit,datediff(now(), fdate) fgap,fctgname from free_board order by fnum desc";
				pstmt1 = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs1 = pstmt1.executeQuery();	
			}else if(opt.equals("0")) {
				String sql = "select fnum, fsubject,date_format(fdate, '%Y.%m.%d') fdate,fhit,datediff(now(), fdate) fgap,fctgname from free_board where fsubject like ? order by fnum desc";
				pstmt1 = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				pstmt1.setString(1, "%" + searchText + "%");
				rs1 = pstmt1.executeQuery();
			}else if(opt.equals("1")) {
				String sql = "select fnum, fsubject,date_format(fdate, '%Y.%m.%d') fdate,fhit,datediff(now(), fdate) fgap,fctgname from free_board where fcontent like ? order by fnum desc";
				pstmt1 = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				pstmt1.setString(1, "%" + searchText + "%");
				rs1 = pstmt1.executeQuery();
			}else if(opt.equals("none")) {
				String sql = "select fnum, fsubject,date_format(fdate, '%Y.%m.%d') fdate,fhit,datediff(now(), fdate) fgap,fctgname from free_board order by fnum desc";
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

			for (int i = 0; i < lLTO.getRecordPerPage() && rs1.next(); i++) {
				FreeBoardTO fTO = new FreeBoardTO();

				fTO.setFnum(rs1.getInt("fnum"));
				fTO.setFsubject(rs1.getString("fsubject"));
				fTO.setFdate(rs1.getString("fdate"));
				fTO.setFhit(rs1.getInt("fhit"));
				fTO.setFgap(rs1.getInt("fgap"));
				fTO.setFctgname(rs1.getString("fctgname"));

				freeList.add(fTO);

			}

			lLTO.setFreeList(freeList);
			lLTO.setStartBlock(((lLTO.getCpage() - 1) / lLTO.getBlockPerPage()) * lLTO.getBlockPerPage() + 1);
			lLTO.setEndBlock(
					((lLTO.getCpage() - 1) / lLTO.getBlockPerPage()) * lLTO.getBlockPerPage() + lLTO.getBlockPerPage());
			System.out.println("start : " + lLTO.getStartBlock());
			if (lLTO.getEndBlock() >= lLTO.getTotalPage()) {
				lLTO.setEndBlock(lLTO.getTotalPage());
			}
			System.out.println("fend: " + lLTO.getEndBlock());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs1 != null)
				try {
					rs1.close();
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

	public int freeWriteOk(FreeBoardTO fTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int flag = 1;
		int fnum = 0;

		try {
			conn = dataSource.getConnection();

			String sql = "select unum from member where uid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fTO.getUid());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				sql = "insert into free_board values(0,?,?,0,now(),?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, fTO.getFsubject());
				pstmt.setString(2, fTO.getFcontent());
				pstmt.setString(3, fTO.getFip());
				pstmt.setString(4, rs.getString("unum"));
				pstmt.setString(5, fTO.getFctgname());
				System.out.println("dao:" + fTO.getFctgname());
			}

			int result = pstmt.executeUpdate();
			if (result == 1) {
				sql = "select fnum from free_board order by fnum desc limit 1";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next())
					fnum = rs.getInt("fnum");

				// flag = 0;
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
		}

		return fnum;
	}

	public FreeBoardTO freeView(FreeBoardTO fTO,String currentUser) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs3 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs4 = null;
		try {
			conn = dataSource.getConnection();

			String sql = "update free_board set fhit=fhit+1 where fnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fTO.getFnum());
			pstmt.executeUpdate();

			sql = "select fsubject,unum,date_format(fdate, '%Y.%m.%d') fdate, fhit,fcontent,fctgname from free_board where fnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fTO.getFnum());
			rs1 = pstmt.executeQuery();

			if (rs1.next()) {

				sql = "select uid from member where unum=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, rs1.getString("unum"));
				rs2 = pstmt.executeQuery();
				
				sql="select count(*) from free_comment where fnum=? group by fnum";
				pstmt2=conn.prepareStatement(sql);
				pstmt2.setInt(1, fTO.getFnum());
				rs3=pstmt2.executeQuery();
				
				sql="select anum from admin where aid=?";
				pstmt3 = conn.prepareStatement(sql);
				pstmt3.setString(1, currentUser);
				rs4 = pstmt3.executeQuery();
				
				if(rs4.next()) {
					fTO.setAnum(rs4.getInt("anum"));
					System.out.println("anum : "+rs4.getInt("anum"));
				}
				if (rs2.next() ) {
					fTO.setUid(rs2.getString("uid"));
				}
				if(rs3.next()) {
					fTO.setCommentNum(rs3.getInt("count(*)"));
				}
				fTO.setFsubject(rs1.getString("fsubject"));
				fTO.setFdate(rs1.getString("fdate"));
				fTO.setFhit(rs1.getInt("fhit"));
				fTO.setFcontent(rs1.getString("fcontent"));
				fTO.setFctgname(rs1.getString("fctgname"));
				
			
			}
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
					rs1.close();
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
			if (rs3 != null)
				try {
					rs1.close();
				} catch (SQLException e) {
				}
			if (pstmt2 != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}

		}
		return fTO;
	}
	
	public FreeBoardTO freeModify(FreeBoardTO fTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			conn = dataSource.getConnection();
			String sql="select fsubject,fcontent,fctgname from free_board where fnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fTO.getFnum());
			rs1=pstmt.executeQuery();
			
			if(rs1.next()) {
	
				fTO.setFsubject(rs1.getString("fsubject"));
				fTO.setFcontent(rs1.getString("fcontent"));
				fTO.setFctgname(rs1.getString("fctgname"));
					
		
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
		
		return fTO;
	}
	
	public int freeModifyOk(FreeBoardTO fTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int flag = 2;
		
		try {
			conn = dataSource.getConnection();


				String sql = "update free_board set fctgname=?,fsubject=?,fcontent=?, fip=? where fnum=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, fTO.getFctgname());
				pstmt.setString(2, fTO.getFsubject());
				pstmt.setString(3, fTO.getFcontent());
				pstmt.setString(4, fTO.getFip());
				pstmt.setInt(5, fTO.getFnum());
		
			
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
	
	public int freerDelete(FreeBoardTO fTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag=2;
		
		try {
			conn = dataSource.getConnection();

			String sql="delete from free_board where fnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fTO.getFnum());
			
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
}
