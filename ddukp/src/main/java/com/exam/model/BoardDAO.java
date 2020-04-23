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

public class BoardDAO {
	private DataSource dataSource = null;

	public BoardDAO() {
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

	public ArrayList<String> movieList(String str) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		ArrayList<String> movieList = new ArrayList<String>();
		try {
			conn = dataSource.getConnection();

			String sql = "select mlsubject from movie_list where mlsubject like ?" ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+str+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				movieList.add(rs.getString("mlsubject"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
					pstmt.close();
				} catch (SQLException e) {
				}
		}

		return movieList;
	}

	public int latterWrite(latterBoardTO lTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		int flag = 1;
		int rnum=0;

		try {
			conn = dataSource.getConnection();
			String sql = "select mlnum from movie_list where mlsubject=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lTO.getMsubject());

			rs1 = pstmt.executeQuery();

			sql = "select unum from member where uid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lTO.getUid());
			rs2 = pstmt.executeQuery();

			if (rs1.next() && rs2.next()) {
				sql = "insert into review_board values(0,?,?,0,now(),?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, lTO.getRsubject());
				pstmt.setString(2, lTO.getRcontent());
				pstmt.setString(3, lTO.getRip());
				pstmt.setString(4, rs2.getString("unum"));
				pstmt.setString(5, lTO.getCtgname());
				pstmt.setString(6, rs1.getString("mlnum"));
				
				System.out.println("dao:"+ lTO.getCtgname());
			}

			int result = pstmt.executeUpdate();
			if (result == 1) {
				sql="select rnum from review_board order by rnum desc limit 1";
				pstmt = conn.prepareStatement(sql);
				rs1 = pstmt.executeQuery();
				if(rs1.next()) rnum=rs1.getInt("rnum");
				
				
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

		return rnum;
	}

	public BoardListTO latterList(BoardListTO lLTO) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;

		
		ArrayList<latterBoardTO> latterList=new ArrayList<latterBoardTO>();
		String opt = lLTO.getSearchKey();
		System.out.println("dao opt : " + opt);
		String searchText = lLTO.getSearchWord();
		System.out.println("dao searchText : " + searchText);
		try {
			conn = dataSource.getConnection();
			
			if(opt == null) {
				String sql = "select rnum, mlnum,rsubject,unum,date_format(rdate, '%Y.%m.%d') rdate,rhit,datediff(now(), rdate) rgap,ctgname from review_board order by rnum desc";
				pstmt1 = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs1 = pstmt1.executeQuery();
			}else if(opt.equals("0")) {
				String sql = "select rnum, mlnum,rsubject,unum,date_format(rdate, '%Y.%m.%d') rdate,rhit,datediff(now(), rdate) rgap,ctgname from review_board where rsubject like ? order by rnum desc";
				pstmt1 = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				pstmt1.setString(1, "%" + searchText + "%");
				rs1 = pstmt1.executeQuery();
			}else if(opt.equals("1")) {
				String sql = "select rnum, mlnum,rsubject,unum,date_format(rdate, '%Y.%m.%d') rdate,rhit,datediff(now(), rdate) rgap,ctgname from review_board where rcontent like ? order by rnum desc";
				pstmt1 = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				pstmt1.setString(1, "%" + searchText + "%");
				rs1 = pstmt1.executeQuery();
			}else if(opt.equals("none")) {
				String sql = "select rnum, mlnum,rsubject,unum,date_format(rdate, '%Y.%m.%d') rdate,rhit,datediff(now(), rdate) rgap,ctgname from review_board order by rnum desc";
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
				latterBoardTO lTO=new latterBoardTO();
				String sql="select mlsubject from movie_list where mlnum=?";
				pstmt2=conn.prepareStatement(sql);
				pstmt2.setString(1, rs1.getString("mlnum"));
				rs2=pstmt2.executeQuery();
				
				sql="select uid from member where unum=?";
				pstmt3=conn.prepareStatement(sql);
				pstmt3.setString(1, rs1.getString("unum"));
				rs3=pstmt3.executeQuery();
				
				if(rs2.next() && rs3.next()) {
					lTO.setRnum(rs1.getInt("rnum"));
					lTO.setMsubject(rs2.getString("mlsubject"));
					lTO.setRsubject(rs1.getString("rsubject"));
					lTO.setUid(rs3.getString("uid"));
					lTO.setRdate(rs1.getString("rdate"));
					lTO.setRhit(rs1.getInt("rhit"));
					lTO.setRgap(rs1.getInt("rgap"));
					lTO.setCtgname(rs1.getString("ctgname"));
					
				}
				latterList.add(lTO);

			}
			
			lLTO.setLatterList(latterList);
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
	
	public latterBoardTO latterView(latterBoardTO lTO,String currentUser) {
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
			
			String sql = "update review_board set rhit=rhit+1 where rnum=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setInt( 1, lTO.getRnum() );
			pstmt.executeUpdate();
			
			sql="select mlnum,rsubject,unum,date_format(rdate, '%Y.%m.%d') rdate, rhit,rcontent from review_board where rnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lTO.getRnum());
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
				
				sql="select count(*) from review_comment where rnum=? group by rnum";
				pstmt2=conn.prepareStatement(sql);
				pstmt2.setInt(1, lTO.getRnum());
				rs4=pstmt2.executeQuery();
				
				sql="select anum from admin where aid=?";
				pstmt3 = conn.prepareStatement(sql);
				pstmt3.setString(1, currentUser);
				rs5 = pstmt3.executeQuery();
				
				if(rs5.next()) {
					lTO.setAnum(rs5.getInt("anum"));
					
				}
				if(rs3.next()) {
					lTO.setUid(rs3.getString("uid"));
				}
				if(rs2.next()) {
					if(rs4.next()) {
						lTO.setCommentNum(rs4.getInt("count(*)"));
					}
					lTO.setMsubject(rs2.getString("mlsubject"));
					lTO.setRsubject(rs1.getString("rsubject"));
					lTO.setRdate(rs1.getString("rdate"));
					lTO.setRhit(rs1.getInt("rhit"));
					lTO.setRcontent(rs1.getString("rcontent"));
					
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
		return lTO;
	}
	
	public latterBoardTO latterModify(latterBoardTO lTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			conn = dataSource.getConnection();
			String sql="select rsubject,mlnum,rcontent,ctgname from review_board where rnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lTO.getRnum());
			rs1=pstmt.executeQuery();
			
			if(rs1.next()) {
				sql="select msubject from movie where mlnum=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, rs1.getString("mlnum"));
				rs2=pstmt.executeQuery();
				
				if(rs2.next()) {
					lTO.setMsubject(rs2.getString("msubject"));
					lTO.setRsubject(rs1.getString("rsubject"));
					lTO.setRcontent(rs1.getString("rcontent"));
					lTO.setCtgname(rs1.getString("ctgname"));
					
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
		
		return lTO;
	}
	public int latterModifyOk(latterBoardTO lTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int flag = 2;
		
		try {
			conn = dataSource.getConnection();

			String sql="select mlnum from movie where msubject=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, lTO.getMsubject());
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				sql = "update review_board set ctgname=?, mlnum=?,rsubject=?,rcontent=?, rip=? where rnum=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, lTO.getCtgname());
				pstmt.setString(2, rs.getString("mlnum"));
				pstmt.setString(3, lTO.getRsubject());
				pstmt.setString(4, lTO.getRcontent());
				pstmt.setString(5, lTO.getRip());
				pstmt.setInt(6, lTO.getRnum());
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
	
	public int latterDelete(latterBoardTO lTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag=2;
		
		try {
			conn = dataSource.getConnection();

			String sql="delete from review_board where rnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lTO.getRnum());
			
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
	public BoardListTO memberWriteList(String id, BoardListTO mLTO, String opt, String searchText) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<latterBoardTO> latterList=new ArrayList<latterBoardTO>();
		
		try {
			conn = dataSource.getConnection();
			String sql = "";
			
			/* uid로 unum 읽어오기 */
			String unum = "";
			sql = "select unum from member where uid = ?";	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				unum = rs.getString("unum");
			}
			
			System.out.println("opt : " + opt + ", searchText : " + searchText);
			
			/* 사용자가 작성한 게시글 목록 */
			if(searchText == "" || searchText == null) {
				System.out.println("searchText empty");
				sql = "(select \"review\" board, unum, rnum num, rsubject subject, rdate date, rhit hit from review_board where unum = ?)" + 
						"union all" + 
						"(select \"free\" board, unum, fnum num, fsubject subject, fdate date, fhit hit from free_board where unum = ?)" + 
						"union all" + 
						"(select \"column\" board, unum, cnum num, csubject subject, cdate date, chit hit from column_board where unum = ?)" + 
						"union all" + 
						"(select \"qna\" board, qnum, qnum num, qsubject subject, qdate date, qhit hit from qna_board where unum = ?)" +
						"order by date desc;";
				pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				pstmt.setString(1, unum);
				pstmt.setString(2, unum);
				pstmt.setString(3, unum);
				pstmt.setString(4, unum);
				
			} else if(opt == "0" || opt.equals("0")) {
				System.out.println("제목 검색");
				sql = "(select \"review\" board, unum, rnum num, rsubject subject, rdate date, rhit hit from review_board where unum = ? and rsubject like ?)" + 
						"union all" + 
						"(select \"free\" board, unum, fnum num, fsubject subject, fdate date, fhit hit from free_board where unum = ? and fsubject like ?)" + 
						"union all" + 
						"(select \"column\" board, unum, cnum num, csubject subject, cdate date, chit hit from column_board where unum = ? and csubject like ?)" + 
						"union all" + 
						"(select \"qna\" board, qnum, qnum num, qsubject subject, qdate date, qhit hit from qna_board where unum = ? and qsubject like ?)" +
						"order by date desc;";
				pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				pstmt.setString(1, unum);
				pstmt.setString(2, "%" + searchText + "%");
				pstmt.setString(3, unum);
				pstmt.setString(4, "%" + searchText + "%");
				pstmt.setString(5, unum);
				pstmt.setString(6, "%" + searchText + "%");
				pstmt.setString(7, unum);
				pstmt.setString(8, "%" + searchText + "%");

			} else if(opt == "1" || opt.equals("1")) {
				System.out.println("내용 검색");
				sql = "(select \"review\" board, unum, rnum num, rsubject subject, rdate date, rhit hit from review_board where unum = ? and rcontent like ?)" + 
						"union all" + 
						"(select \"free\" board, unum, fnum num, fsubject subject, fdate date, fhit hit from free_board where unum = ? and fcontent like ?)" + 
						"union all" + 
						"(select \"column\" board, unum, cnum num, csubject subject, cdate date, chit hit from column_board where unum = ? and ccontent like ?)" + 
						"union all" + 
						"(select \"qna\" board, qnum, qnum num, qsubject subject, qdate date, qhit hit from qna_board where unum = ? and qcontent like ?)" +
						"order by date desc;";
				pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				pstmt.setString(1, unum);
				pstmt.setString(2, "%" + searchText + "%");
				pstmt.setString(3, unum);
				pstmt.setString(4, "%" + searchText + "%");
				pstmt.setString(5, unum);
				pstmt.setString(6, "%" + searchText + "%");
				pstmt.setString(7, unum);
				pstmt.setString(8, "%" + searchText + "%");

			} else if(opt == "none" || opt.equals("none")) {
				System.out.println("전체 검색");
				sql = "(select \"review\" board, unum, rnum num, rsubject subject, rdate date, rhit hit from review_board where unum = ? and (rsubject like ? or rcontent like ?))" + 
						"union all" + 
						"(select \"free\" board, unum, fnum num, fsubject subject, fdate date, fhit hit from free_board where unum = ? and (fsubject like ? or fcontent like ?))" + 
						"union all" + 
						"(select \"column\" board, unum, cnum num, csubject subject, cdate date, chit hit from column_board where unum = ? and (csubject like ? or ccontent like ?))" + 
						"union all" + 
						"(select \"qna\" board, qnum, qnum num, qsubject subject, qdate date, qhit hit from qna_board where unum = ? and (qsubject like ? or qcontent like ?))" +
						"order by date desc;";
				pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				pstmt.setString(1, unum);
				pstmt.setString(2, "%" + searchText + "%");
				pstmt.setString(3, "%" + searchText + "%");
				pstmt.setString(4, unum);
				pstmt.setString(5, "%" + searchText + "%");
				pstmt.setString(6, "%" + searchText + "%");
				pstmt.setString(7, unum);
				pstmt.setString(8, "%" + searchText + "%");
				pstmt.setString(9, "%" + searchText + "%");
				pstmt.setString(10, unum);
				pstmt.setString(11, "%" + searchText + "%");
				pstmt.setString(12, "%" + searchText + "%");
			}
			rs = pstmt.executeQuery();

			/* 페이지 설정 */
			rs.last();
			mLTO.setTotalRecord(rs.getRow());
			rs.beforeFirst();
			
			mLTO.setTotalPage((mLTO.getTotalRecord() - 1) / mLTO.getRecordPerPage() + 1);
			
			int skip = (mLTO.getCpage() - 1) * mLTO.getRecordPerPage();
			if (skip != 0)
				rs.absolute(skip);
			
			for (int i=0; i<mLTO.getRecordPerPage() && rs.next();i++) {
				latterBoardTO lTO = new latterBoardTO();

				lTO.setRnum(rs.getInt("num"));
				lTO.setCtgname(rs.getString("board"));
				lTO.setRsubject(rs.getString("subject"));
				lTO.setRdate(rs.getString("date"));
				lTO.setRhit(rs.getInt("hit"));
					
				latterList.add(lTO);
			}
			
			mLTO.setLatterList(latterList);
			mLTO.setStartBlock(((mLTO.getCpage() - 1) / mLTO.getBlockPerPage()) * mLTO.getBlockPerPage() + 1);
			mLTO.setEndBlock(((mLTO.getCpage() - 1) / mLTO.getBlockPerPage()) * mLTO.getBlockPerPage() + mLTO.getBlockPerPage());
			System.out.println("start : "+mLTO.getStartBlock());
			
			if (mLTO.getEndBlock() >= mLTO.getTotalPage()) {
				mLTO.setEndBlock(mLTO.getTotalPage());
			}
			System.out.println("end: "+mLTO.getEndBlock());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)try {rs.close();} catch (SQLException e) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException e) {}
			if (conn != null)try {conn.close();} catch (SQLException e) {}
		}
		return mLTO;
	}
	
}
