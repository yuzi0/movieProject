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

public class CusBoardDAO {
	private DataSource dataSource = null;

	public CusBoardDAO() {
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
	
	public BoardListTO customerList(BoardListTO lLTO) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;

		
		ArrayList<CusBoardTO> customerList=new ArrayList<CusBoardTO>();
		
		try {
			conn = dataSource.getConnection();
			String sql = "select qnum,qsubject,unum,date_format(qdate, '%Y.%m.%d') qdate,qhit,datediff(now(), qdate) qgap,qctgname from qna_board order by qnum desc";
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
				CusBoardTO qTO=new CusBoardTO();

				sql="select uid from member where unum=?";
				pstmt3=conn.prepareStatement(sql);
				pstmt3.setString(1, rs1.getString("unum"));
				rs3=pstmt3.executeQuery();
				
				if( rs3.next()) {
					qTO.setQnum(rs1.getInt("qnum"));
					qTO.setQsubject(rs1.getString("qsubject"));
					qTO.setUid(rs3.getString("uid"));
					qTO.setQdate(rs1.getString("qdate"));
					qTO.setQhit(rs1.getInt("qhit"));
					qTO.setQgap(rs1.getInt("qgap"));
					qTO.setQctgname(rs1.getString("qctgname"));
					
				}
				customerList.add(qTO);

			}
			
			lLTO.setCustomerList(customerList);
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
	
	public int customerCenterWrite(CusBoardTO qTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		int flag = 1;
		int qnum=0;

		try {
			conn = dataSource.getConnection();

			String sql = "select unum from member where uid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qTO.getUid());
			rs2 = pstmt.executeQuery();

			if (rs2.next()) {
				sql = "insert into qna_board values(0,?,?,0,now(),?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, qTO.getQsubject());
				pstmt.setString(2, qTO.getQcontent());
				pstmt.setString(3, qTO.getQip());
				pstmt.setString(4, rs2.getString("unum"));
				pstmt.setString(5, qTO.getQctgname());
			}

			int result = pstmt.executeUpdate();
			if (result == 1) {
				sql="select qnum from qna_board order by qnum desc limit 1";
				pstmt = conn.prepareStatement(sql);
				rs1 = pstmt.executeQuery();
				if(rs1.next()) qnum=rs1.getInt("qnum");
				
				System.out.println("dao : "+qnum);
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

		return qnum;
	}

	public CusBoardTO customerCenterView(CusBoardTO qTO,String currentUser) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs4 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs5 = null;
		try {
			conn = dataSource.getConnection();
			
			String sql = "update qna_board set qhit=qhit+1 where qnum=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setInt( 1, qTO.getQnum() );
			pstmt.executeUpdate();
			
			sql="select qsubject,unum,date_format(qdate, '%Y.%m.%d') qdate,qctgname, qhit,qcontent from qna_board where qnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qTO.getQnum());
			rs1=pstmt.executeQuery();
			
			if(rs1.next()) {

				sql="select uid from member where unum=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, rs1.getString("unum"));
				rs3=pstmt.executeQuery();
				
				sql="select anum from admin where aid=?";
				pstmt3 = conn.prepareStatement(sql);
				pstmt3.setString(1, currentUser);
				rs4 = pstmt3.executeQuery();
				
				sql="select count(*) from qna_comment where qnum=? group by qnum";
				pstmt4=conn.prepareStatement(sql);
				pstmt4.setInt(1, qTO.getQnum());
				rs5=pstmt4.executeQuery();
				
				if(rs4.next()) {
					qTO.setAnum(rs4.getInt("anum"));
					System.out.println("anum : "+rs4.getInt("anum"));
				}
				
				if( rs3.next()) {
					if(rs5.next()) {
						qTO.setCommentNum(rs5.getInt("count(*)"));
					}
					qTO.setQsubject(rs1.getString("qsubject"));
					qTO.setUid(rs3.getString("uid"));
					qTO.setQdate(rs1.getString("qdate"));
					qTO.setQhit(rs1.getInt("qhit"));
					qTO.setQcontent(rs1.getString("qcontent"));
					qTO.setQctgname(rs1.getString("qctgname"));
					
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
		return qTO;
	}
	public CusBoardTO customerCenterModify(CusBoardTO qTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			conn = dataSource.getConnection();
			String sql="select qsubject,qcontent,qctgname from qna_board where qnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qTO.getQnum());
			rs1=pstmt.executeQuery();
			
			if(rs1.next()) {


					qTO.setQsubject(rs1.getString("qsubject"));
					qTO.setQcontent(rs1.getString("qcontent"));
					qTO.setQctgname(rs1.getString("qctgname"));
					
				
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
		
		return qTO;
	}
	
	public int customerCenterModifyOk(CusBoardTO qTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int flag = 2;
		
		try {
			conn = dataSource.getConnection();

		
				String sql = "update qna_board set qctgname=?, qsubject=?,qcontent=?, qip=? where qnum=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, qTO.getQctgname());
				pstmt.setString(2, qTO.getQsubject());
				pstmt.setString(3, qTO.getQcontent());
				pstmt.setString(4, qTO.getQip());
				pstmt.setInt(5, qTO.getQnum());
			
			
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
	
	public int customerCenterDelete(CusBoardTO qTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag=2;
		
		try {
			conn = dataSource.getConnection();

			String sql="delete from qna_board where qnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qTO.getQnum());
			
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
