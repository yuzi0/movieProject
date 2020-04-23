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

public class MovieDAO {
	private DataSource dataSource = null;

	public MovieDAO() {
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
	
	public int movieSortWrite(ArrayList<MovieSortTO> msortlist) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		int flag = 1;
		int result=0;
		try {
			conn = dataSource.getConnection();
	
			for(MovieSortTO msTO:msortlist) {
				String sql="insert into movie_list values(0,?,?,?,?,0)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, msTO.getMsubject().substring(0, msTO.getMsubject().indexOf("(")));
				pstmt.setString(2, msTO.getScore());
				pstmt.setString(3, msTO.getImg());
				pstmt.setString(4, msTO.getGenre());
				result+=pstmt.executeUpdate();
			}
			if(result==msortlist.size()) {
				flag=0;
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
			if (conn != null) try { conn.close(); } catch (SQLException e) {}
			if (rs1 != null) try { rs1.close(); } catch (SQLException e) {}
			if (rs2 != null) try { rs2.close(); } catch (SQLException e) {}
		}

		return flag;
	}
	public MovieSortListTO movieSortList(MovieSortListTO msLTO,String category) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;

		
		ArrayList<MovieSortTO> moviesortList=new ArrayList<MovieSortTO>();
		
		try {
			conn = dataSource.getConnection();
			String sql="";
			if(category==null || category.equals("all")) {
				sql = "select mlnum,mlsubject,mlsore,mlimg from movie_list";
			}
			else if(category.equals("action")) {
				sql = "select mlnum,mlsubject,mlsore,mlimg from movie_list where mlgenre='액션'";
			}
			else if(category.equals("comedy")) {
				sql = "select mlnum,mlsubject,mlsore,mlimg from movie_list where mlgenre='코미디'";
			}
			else if(category.equals("horror")) {
				sql = "select mlnum,mlsubject,mlsore,mlimg from movie_list where mlgenre='공포/스릴러'";
			}
			else if(category.equals("drama")) {
				sql = "select mlnum,mlsubject,mlsore,mlimg from movie_list where mlgenre='드라마'";
			}
			else if(category.equals("melo")) {
				sql = "select mlnum,mlsubject,mlsore,mlimg from movie_list where mlgenre='멜로'";
			}
			
			else if(category.equals("sf")) {
				sql = "select mlnum,mlsubject,mlsore,mlimg from movie_list where mlgenre='SF/판타지'";
			}
			
			pstmt1 = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs1 = pstmt1.executeQuery();

			rs1.last();
			msLTO.setTotalRecord(rs1.getRow());
			rs1.beforeFirst();
			
			msLTO.setTotalPage((msLTO.getTotalRecord() - 1) / msLTO.getRecordPerPage() + 1);
			
			int skip = (msLTO.getCpage() - 1) * msLTO.getRecordPerPage();
			if (skip != 0)
				rs1.absolute(skip);
			
			for (int i=0; i<msLTO.getRecordPerPage() && rs1.next();i++) {
				MovieSortTO mTO=new MovieSortTO();
				mTO.setMnum(rs1.getInt("mlnum"));
				mTO.setMsubject(rs1.getString("mlsubject"));
				mTO.setScore(rs1.getString("mlsore"));
				mTO.setImg(rs1.getString("mlimg"));
				
				moviesortList.add(mTO);

			}
			
			msLTO.setMovieallList(moviesortList);
			msLTO.setStartBlock(((msLTO.getCpage() - 1) / msLTO.getBlockPerPage()) * msLTO.getBlockPerPage() + 1);
			msLTO.setEndBlock(((msLTO.getCpage() - 1) / msLTO.getBlockPerPage()) * msLTO.getBlockPerPage() + msLTO.getBlockPerPage());
			System.out.println("start : "+msLTO.getStartBlock());
			if (msLTO.getEndBlock() >= msLTO.getTotalPage()) {
				msLTO.setEndBlock(msLTO.getTotalPage());
			}
			System.out.println("end: "+msLTO.getEndBlock());
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
		return msLTO;
	}
	
	public MovieSortTO movieInfo(int mnum) {
		MovieSortTO to = new MovieSortTO();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "update movie_list set mlhit=mlhit+1 where mlnum=?";
			pstmt2 = conn.prepareStatement( sql );
			pstmt2.setInt( 1,mnum );
			pstmt2.executeUpdate();
			
			sql="select * from  movie_list where mlnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mnum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				to.setGenre(rs.getString("mlgenre"));
				to.setImg(rs.getString("mlimg"));
				to.setMsubject(rs.getString("mlsubject"));
				to.setScore(rs.getString("mlsore"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) { }
			if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { }
			if (conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return to;
	}
	
	public MovieSortListTO mainMovie() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<MovieSortTO> moviesortList=new ArrayList<MovieSortTO>();
		MovieSortListTO msLTO = new MovieSortListTO();
		
		try {
			conn = dataSource.getConnection();
			String sql="select mlnum,mlsubject,mlsore,mlimg,mlgenre from movie_list order by mlhit desc limit 8";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			
			for (int i=0; i<msLTO.getRecordPerPage() && rs.next();i++) {
				MovieSortTO mTO=new MovieSortTO();
				mTO.setMnum(rs.getInt("mlnum"));
				mTO.setMsubject(rs.getString("mlsubject"));
				mTO.setScore(rs.getString("mlsore"));
				mTO.setImg(rs.getString("mlimg"));
				mTO.setGenre(rs.getString("mlgenre"));
				moviesortList.add(mTO);
			}
			msLTO.setMovieallList(moviesortList);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {}
			if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
			if (conn != null) try { conn.close(); } catch (SQLException e) {}
		}
		return msLTO;
	}
}
