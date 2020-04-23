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

public class InfoBoardDAO {
	private DataSource dataSource = null;

	public InfoBoardDAO() {
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
	
	public BoardListTO latterList(BoardListTO lLTO) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;

		
		ArrayList<InfoBoardTO> infoList=new ArrayList<InfoBoardTO>();
		
		try {
			conn = dataSource.getConnection();
			String sql = "select inum, anum,isubject,date_format(idate, '%Y.%m.%d') idate,ihit,datediff(now(), idate) igap,ictgname from info_board order by inum desc";
			pstmt1 = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs1 = pstmt1.executeQuery();

			rs1.last();
			lLTO.setTotalRecord(rs1.getRow());
			rs1.beforeFirst();
			
			lLTO.setTotalPage((lLTO.getTotalRecord() - 1) / lLTO.getRecordPerPage() + 1);
			
			int skip = (lLTO.getCpage() - 1) * lLTO.getRecordPerPage();
			if (skip != 0)
				rs1.absolute(skip);
			
			for (int i=0; i<lLTO.getRecordPerPage() && rs1.next();i++) {
				InfoBoardTO iTO=new InfoBoardTO();
	
				
				sql="select aid from admin where anum=?";
				pstmt3=conn.prepareStatement(sql);
				pstmt3.setString(1, rs1.getString("anum"));
				rs2=pstmt3.executeQuery();
				
				if(rs2.next()) {
					iTO.setInum(rs1.getInt("inum"));
					iTO.setIsubject(rs1.getString("isubject"));
					iTO.setAid(rs2.getString("aid"));
					iTO.setIdate(rs1.getString("idate"));
					iTO.setIhit(rs1.getInt("ihit"));
					iTO.setIgap(rs1.getInt("igap"));
					iTO.setIctgname(rs1.getString("ictgname"));
					
				}
				infoList.add(iTO);

			}
			
			lLTO.setInfoList(infoList);
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
	
	public int infoWrite(InfoBoardTO iTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		int flag = 1;
		int inum=0;

		try {
			conn = dataSource.getConnection();

			String sql = "select anum from admin where aid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iTO.getAid());

			rs1 = pstmt.executeQuery();
			if(rs1.next()) {
				sql = "insert into info_board values(0,?,?,0,now(),?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, iTO.getIsubject());
				pstmt.setString(2, iTO.getIcontent());
				pstmt.setString(3, iTO.getIip());
				pstmt.setString(4, rs1.getString("anum"));
				pstmt.setString(5, iTO.getIctgname());
			}

			int result = pstmt.executeUpdate();
			if (result == 1) {
				sql="select inum from info_board order by inum desc limit 1";
				pstmt = conn.prepareStatement(sql);
				rs1 = pstmt.executeQuery();
				if(rs1.next()) inum=rs1.getInt("inum");
				
				
				//flag = 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		return inum;
	}
	
	public InfoBoardTO infoView(InfoBoardTO iTO,String currentUser) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs4 = null;
		try {
			conn = dataSource.getConnection();
			
			String sql = "update info_board set ihit=ihit+1 where inum=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setInt( 1, iTO.getInum() );
			pstmt.executeUpdate();
			
			sql="select isubject,anum,date_format(idate, '%Y.%m.%d') idate, ihit,icontent,ictgname from info_board where inum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iTO.getInum());
			rs1=pstmt.executeQuery();
			
			if(rs1.next()) {
				
	
				sql="select anum from admin where aid=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, currentUser);
				rs3 = pstmt.executeQuery();
				
				sql="select count(*) from info_comment where inum=? group by inum";
				pstmt2=conn.prepareStatement(sql);
				pstmt2.setInt(1, iTO.getInum());
				rs4=pstmt2.executeQuery();
				
				if(rs3.next()) {
					iTO.setAnum(rs3.getInt("anum"));
					System.out.println("anum : "+rs3.getInt("anum"));
				}
				
					if(rs4.next()) {
						iTO.setCommentNum(rs4.getInt("count(*)"));
					}
					iTO.setIsubject(rs1.getString("isubject"));
					iTO.setIdate(rs1.getString("idate"));
					iTO.setIhit(rs1.getInt("ihit"));
					iTO.setIcontent(rs1.getString("icontent"));
					iTO.setIctgname(rs1.getString("ictgname"));
					
				
				
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

			if (rs4 != null)
				try {
					rs3.close();
				} catch (SQLException e) {
				}
			if (pstmt2 != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
		}
		return iTO;
	}
	
	public InfoBoardTO infoModify(InfoBoardTO iTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			conn = dataSource.getConnection();
			String sql="select isubject,icontent,ictgname from info_board where inum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iTO.getInum());
			rs1=pstmt.executeQuery();
			
			if(rs1.next()) {

					iTO.setIsubject(rs1.getString("isubject"));
					iTO.setIcontent(rs1.getString("icontent"));
					iTO.setIctgname(rs1.getString("ictgname"));
					
				
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
		
		return iTO;
	}
	
	public int infoModifyOk(InfoBoardTO iTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int flag = 2;
		
		try {
			conn = dataSource.getConnection();

		
				String sql = "update info_board set ictgname=?,isubject=?,icontent=?, iip=? where inum=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, iTO.getIctgname());
				pstmt.setString(2, iTO.getIsubject());
				pstmt.setString(3, iTO.getIcontent());
				pstmt.setString(4, iTO.getIip());
				pstmt.setInt(5, iTO.getInum());
			
			
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
	
	public int infoDelete(InfoBoardTO iTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag=2;
		
		try {
			conn = dataSource.getConnection();

			String sql="delete from info_board where inum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iTO.getInum());
			
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
