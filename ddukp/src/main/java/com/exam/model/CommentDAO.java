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




public class CommentDAO {
	private DataSource dataSource;
	
	public CommentDAO() {
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
	
	public int freeCommentOk(FreeCommentTO fcTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int flag = 1;
		
		try {
			conn=dataSource.getConnection();
			
			String sql = "select unum from member where uid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fcTO.getUid());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
			sql="insert into free_comment values(0,?,?,now(),?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, fcTO.getFccontent());
			pstmt.setString(2, fcTO.getFcip());
			pstmt.setString(3, fcTO.getFnum());
			pstmt.setString(4, rs.getString("unum"));
			
			}
			int result=pstmt.executeUpdate();
			
			if(result==1) flag=0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
public ArrayList<FreeCommentTO> freeCommentList(FreeCommentTO fcTO){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs3 = null;
		
		ArrayList<FreeCommentTO> fcLists = new ArrayList<FreeCommentTO>();
		try {
			conn = dataSource.getConnection();
			
			//String sql = "select fcnum,fnum,fccontent,date_format(fcdate, '%Y.%m.%d %H:%i') fcdate from free_comment where fnum=? order by fcnum asc";
			//String sql="select @rownum:=@rownum+1 as number, fccontent,fcdate from free_comment where (@rownum:=0)=0 and fnum=? order by fcdate";
			String sql=" select @rownum:=@rownum+1 as number, b.* from free_comment b,(select @rownum:=0) r where fnum=? order by fcdate";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString(1, fcTO.getFnum());
			rs = pstmt.executeQuery();
			

			
			while( rs.next() ) {
				sql="select uid from member where unum=?";
				pstmt2 = conn.prepareStatement( sql );
				pstmt2.setString(1, rs.getString("unum"));
				rs2=pstmt2.executeQuery();
				while(rs2.next()) {
				FreeCommentTO cTTo=new FreeCommentTO();
				cTTo.setFccontent(rs.getString("fccontent"));
				cTTo.setFcdate(rs.getString("fcdate"));
				cTTo.setNumber(rs.getInt("number"));
				cTTo.setFcnum(rs.getString("fcnum"));
				cTTo.setUnum(rs.getString("unum"));
				cTTo.setUid(rs2.getString("uid"));

				fcLists.add( cTTo );
				}
			}
			
		

		} catch(SQLException e) {
			System.out.println("[에러] " + e.getMessage());
		} finally {
			if(rs != null) try { rs.close(); } catch( SQLException e ) {}
			if(pstmt != null) try { pstmt.close(); } catch( SQLException e ) {}
			if(conn != null) try { conn.close(); } catch( SQLException e ) {}
		}
		return fcLists;
		
	}

public int freeCommentDelete(FreeCommentTO fcTO) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int flag = 1;
	
	try {
		conn=dataSource.getConnection();

		String sql = "delete from free_comment where fcnum=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, fcTO.getFcnum());
		
		
		int result=pstmt.executeUpdate();
		
		if(result==1) flag=0;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return flag;
}
public int freeCommentModify(FreeCommentTO fcTO) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int flag = 1;
	
	try {
		conn=dataSource.getConnection();

		String sql = "update free_comment set fccontent=? where fcnum=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, fcTO.getFcnum());
		
		
		int result=pstmt.executeUpdate();
		
		if(result==1) flag=0;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return flag;
}

public int columnCommentOk(ColumnCommentTO ccTO) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int flag = 1;
	
	try {
		conn=dataSource.getConnection();
		
		String sql = "select unum from member where uid=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, ccTO.getUid());
		rs = pstmt.executeQuery();
		
		if (rs.next()) {
		sql="insert into column_comment values(0,?,?,now(),?,?)";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, ccTO.getCccontent());
		pstmt.setString(2, ccTO.getCcip());
		pstmt.setString(3, ccTO.getCnum());
		pstmt.setString(4, rs.getString("unum"));
		
		}
		int result=pstmt.executeUpdate();
		
		if(result==1) flag=0;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return flag;
}

public ArrayList<ColumnCommentTO> columnCommentList(ColumnCommentTO ccTO){
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs2 = null;
	
	
	
	ArrayList<ColumnCommentTO> ccLists = new ArrayList<ColumnCommentTO>();
	try {
		conn = dataSource.getConnection();
		
		//String sql = "select fcnum,fnum,fccontent,date_format(fcdate, '%Y.%m.%d %H:%i') fcdate from free_comment where fnum=? order by fcnum asc";
		//String sql="select @rownum:=@rownum+1 as number, fccontent,fcdate from free_comment where (@rownum:=0)=0 and fnum=? order by fcdate";
		String sql=" select @rownum:=@rownum+1 as number, b.* from column_comment b,(select @rownum:=0) r where cnum=? order by ccdate";
		pstmt = conn.prepareStatement( sql );
		pstmt.setString(1, ccTO.getCnum());
		
		rs = pstmt.executeQuery();
		while( rs.next() ) {
			
			sql="select uid from member where unum=?";
			pstmt2 = conn.prepareStatement( sql );
			pstmt2.setString(1, rs.getString("unum"));
			rs2=pstmt2.executeQuery();
			while(rs2.next()) {
			ColumnCommentTO cTTo=new ColumnCommentTO();
			cTTo.setCccontent(rs.getString("cccontent"));
			cTTo.setCcdate(rs.getString("ccdate"));
			cTTo.setNumber(rs.getInt("number"));
			cTTo.setCcnum(rs.getString("ccnum"));
			cTTo.setUnum(rs.getString("unum"));
			cTTo.setUid(rs2.getString("uid"));
			ccLists.add( cTTo );
			}
		}
		
	

	} catch(SQLException e) {
		System.out.println("[에러] " + e.getMessage());
	} finally {
		if(rs != null) try { rs.close(); } catch( SQLException e ) {}
		if(pstmt != null) try { pstmt.close(); } catch( SQLException e ) {}
		if(conn != null) try { conn.close(); } catch( SQLException e ) {}
	}
	return ccLists;
	
}

public int columnCommentDelete(ColumnCommentTO ccTO) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int flag = 1;
	
	try {
		conn=dataSource.getConnection();

		String sql = "delete from column_comment where ccnum=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, ccTO.getCcnum());
		
		
		int result=pstmt.executeUpdate();
		
		if(result==1) flag=0;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return flag;
}
public int latterCommentOk(LatterCommentTO lcTO) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int flag = 1;
	
	try {
		conn=dataSource.getConnection();
		
		String sql = "select unum from member where uid=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, lcTO.getUid());
		rs = pstmt.executeQuery();
		
		if (rs.next()) {
		sql="insert into review_comment values(0,?,?,now(),?,?)";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, lcTO.getRccontent());
		pstmt.setString(2, lcTO.getRcip());
		pstmt.setString(3, lcTO.getRnum());
		pstmt.setString(4, rs.getString("unum"));
		
		}
		int result=pstmt.executeUpdate();
		
		if(result==1) flag=0;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return flag;
}
public ArrayList<LatterCommentTO> latterCommentList(LatterCommentTO lcTO){
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs2 = null;
	
	ArrayList<LatterCommentTO> fcLists = new ArrayList<LatterCommentTO>();
	try {
		conn = dataSource.getConnection();
		
		//String sql = "select fcnum,fnum,fccontent,date_format(fcdate, '%Y.%m.%d %H:%i') fcdate from free_comment where fnum=? order by fcnum asc";
		//String sql="select @rownum:=@rownum+1 as number, fccontent,fcdate from free_comment where (@rownum:=0)=0 and fnum=? order by fcdate";
		String sql=" select @rownum:=@rownum+1 as number, b.* from review_comment b,(select @rownum:=0) r where rnum=? order by rcdate";
		pstmt = conn.prepareStatement( sql );
		pstmt.setString(1, lcTO.getRnum());
		
		rs = pstmt.executeQuery();
		while( rs.next() ) {
		
		sql="select uid from member where unum=?";
		pstmt2 = conn.prepareStatement( sql );
		pstmt2.setString(1, rs.getString("unum"));
		rs2=pstmt2.executeQuery();
		while(rs2.next()) {
			LatterCommentTO lTTo=new LatterCommentTO();
			lTTo.setRccontent(rs.getString("rccontent"));
			lTTo.setRcdate(rs.getString("rcdate"));
			lTTo.setNumber(rs.getInt("number"));
			lTTo.setRcnum(rs.getString("rcnum"));
			lTTo.setUnum(rs.getString("unum"));
			lTTo.setUid(rs2.getString("uid"));
			fcLists.add( lTTo );
		}
			
			
			
		}
		
		
		
	

	} catch(SQLException e) {
		System.out.println("[에러] " + e.getMessage());
	} finally {
		if(rs != null) try { rs.close(); } catch( SQLException e ) {}
		if(pstmt != null) try { pstmt.close(); } catch( SQLException e ) {}
		if(conn != null) try { conn.close(); } catch( SQLException e ) {}
	}
	return fcLists;
	
}
public int latterCommentDelete(LatterCommentTO lcTO) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int flag = 1;
	
	try {
		conn=dataSource.getConnection();

		String sql = "delete from review_comment where rcnum=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, lcTO.getRcnum());
		
		
		int result=pstmt.executeUpdate();
		
		if(result==1) flag=0;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return flag;
}

public int infoCommentOk(InfoCommentTO icTO) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int flag = 1;
	
	try {
		conn=dataSource.getConnection();
		
		String sql = "select unum from member where uid=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, icTO.getUid());
		rs = pstmt.executeQuery();
		
		if (rs.next()) {
		sql="insert into info_comment values(0,?,?,now(),?,?)";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, icTO.getIccontent());
		pstmt.setString(2, icTO.getIcip());
		pstmt.setString(3, icTO.getInum());
		pstmt.setString(4, rs.getString("unum"));
		
		}
		int result=pstmt.executeUpdate();
		
		if(result==1) flag=0;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return flag;
}
public ArrayList<InfoCommentTO> infoCommentList(InfoCommentTO icTO){
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs2 = null;
	
	ArrayList<InfoCommentTO> fcLists = new ArrayList<InfoCommentTO>();
	try {
		conn = dataSource.getConnection();
		
		//String sql = "select fcnum,fnum,fccontent,date_format(fcdate, '%Y.%m.%d %H:%i') fcdate from free_comment where fnum=? order by fcnum asc";
		//String sql="select @rownum:=@rownum+1 as number, fccontent,fcdate from free_comment where (@rownum:=0)=0 and fnum=? order by fcdate";
		String sql=" select @rownum:=@rownum+1 as number, b.* from info_comment b,(select @rownum:=0) r where inum=? order by icdate";
		pstmt = conn.prepareStatement( sql );
		pstmt.setString(1, icTO.getInum());

		rs = pstmt.executeQuery();
		while( rs.next() ) {
			sql="select uid from member where unum=?";
			pstmt2 = conn.prepareStatement( sql );
			pstmt2.setString(1, rs.getString("unum"));
			rs2=pstmt2.executeQuery();
			while(rs2.next()) {
			InfoCommentTO cTTo=new InfoCommentTO();
			cTTo.setIccontent(rs.getString("iccontent"));
			cTTo.setIcdate(rs.getString("icdate"));
			cTTo.setNumber(rs.getInt("number"));
			cTTo.setIcnum(rs.getString("icnum"));
			cTTo.setUnum(rs.getString("unum"));
			cTTo.setUid(rs2.getString("uid"));
			fcLists.add( cTTo );
			}
		}
		
	

	} catch(SQLException e) {
		System.out.println("[에러] " + e.getMessage());
	} finally {
		if(rs != null) try { rs.close(); } catch( SQLException e ) {}
		if(pstmt != null) try { pstmt.close(); } catch( SQLException e ) {}
		if(rs2 != null) try { rs.close(); } catch( SQLException e ) {}
		if(pstmt2 != null) try { pstmt.close(); } catch( SQLException e ) {}
		if(conn != null) try { conn.close(); } catch( SQLException e ) {}
	}
	return fcLists;
	
}

public int infoCommentDelete(InfoCommentTO icTO) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int flag = 1;
	
	try {
		conn=dataSource.getConnection();

		String sql = "delete from info_comment where icnum=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, icTO.getIcnum());
		System.out.println("dao icnum"+ icTO.getIcnum());
		
		int result=pstmt.executeUpdate();
		
		if(result==1) flag=0;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return flag;
}


public int customerCommentOk(CusCommentTO qcTO) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs2 = null;
	int flag = 1;
	
	try {
		conn=dataSource.getConnection();
		
		String sql = "select unum from member where uid=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, qcTO.getUid());
		rs = pstmt.executeQuery();
		
		sql="select anum from admin where aid=?";
		pstmt2 = conn.prepareStatement(sql);
		pstmt2.setString(1, qcTO.getUid());
		rs2 = pstmt2.executeQuery();
		
		if (rs.next()) {
			System.out.println("사용자");
		sql="insert into qna_comment(qcnum,qccontent,qcip,qcdate,qnum,unum) values(0,?,?,now(),?,?)";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, qcTO.getQccontent());
		pstmt.setString(2, qcTO.getQcip());
		pstmt.setString(3, qcTO.getQnum());
		pstmt.setString(4, rs.getString("unum"));
		
		}
		if (rs2.next()){
			System.out.println("관ㄹ지ㅏ");
			
			
			sql="insert into qna_comment(qcnum,qccontent,qcip,qcdate,qnum,anum) values(0,?,?,now(),?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, qcTO.getQccontent());
			pstmt.setString(2, qcTO.getQcip());
			pstmt.setString(3, qcTO.getQnum());
			pstmt.setString(4, rs2.getString("anum"));
		}
		int result=pstmt.executeUpdate();
		
		if(result==1) flag=0;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return flag;
}

public ArrayList<CusCommentTO> customerCommentList(CusCommentTO qcTO){
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs2 = null;
	PreparedStatement pstmt3 = null;
	ResultSet rs3 = null;
	
	ArrayList<CusCommentTO> fcLists = new ArrayList<CusCommentTO>();
	try {
		conn = dataSource.getConnection();
		
		//String sql = "select fcnum,fnum,fccontent,date_format(fcdate, '%Y.%m.%d %H:%i') fcdate from free_comment where fnum=? order by fcnum asc";
		//String sql="select @rownum:=@rownum+1 as number, fccontent,fcdate from free_comment where (@rownum:=0)=0 and fnum=? order by fcdate";
		String sql=" select @rownum:=@rownum+1 as number, b.* from qna_comment b,(select @rownum:=0) r where qnum=? order by qcdate";
		pstmt = conn.prepareStatement( sql );
		pstmt.setString(1, qcTO.getQnum());
		
		rs = pstmt.executeQuery();
		while( rs.next() ) {
			sql="select uid from member where unum=?";
			pstmt2 = conn.prepareStatement( sql );
			pstmt2.setString(1, rs.getString("unum"));
			rs2=pstmt2.executeQuery();
			
			sql="select aid from admin where anum=?";
			pstmt3 = conn.prepareStatement( sql );
			pstmt3.setString(1, rs.getString("anum"));
			rs3=pstmt3.executeQuery();
			
			while(rs2.next()) {
			CusCommentTO cTTo=new CusCommentTO();
			cTTo.setQccontent(rs.getString("qccontent"));
			cTTo.setQcdate(rs.getString("qcdate"));
			cTTo.setNumber(rs.getInt("number"));
			cTTo.setQcnum(rs.getString("qcnum"));
			cTTo.setUnum(rs.getString("unum"));
			cTTo.setUid(rs2.getString("uid"));
			fcLists.add( cTTo );
			}
			
			while(rs3.next()) {
				CusCommentTO cTTo=new CusCommentTO();
				cTTo.setQccontent(rs.getString("qccontent"));
				cTTo.setQcdate(rs.getString("qcdate"));
				cTTo.setNumber(rs.getInt("number"));
				cTTo.setQcnum(rs.getString("qcnum"));
				cTTo.setAnum(rs.getString("anum"));
				cTTo.setAid(rs3.getString("aid"));
				fcLists.add( cTTo );
			
			}
		}
		
	

	} catch(SQLException e) {
		System.out.println("[에러] " + e.getMessage());
	} finally {
		if(rs != null) try { rs.close(); } catch( SQLException e ) {}
		if(pstmt != null) try { pstmt.close(); } catch( SQLException e ) {}
		if(conn != null) try { conn.close(); } catch( SQLException e ) {}
	}
	return fcLists;
	
}
public int customerCommentDelete(CusCommentTO qcTO) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int flag = 1;
	
	try {
		conn=dataSource.getConnection();

		String sql = "delete from qna_comment where qcnum=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, qcTO.getQcnum());
		
		
		int result=pstmt.executeUpdate();
		
		if(result==1) flag=0;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return flag;
}

public int adminCheck(String currentUser) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int anum=0;
	try {
		conn=dataSource.getConnection();

		String sql = "select anum from admin where aid=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,currentUser);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			anum=rs.getInt("anum");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return anum;
}
}
