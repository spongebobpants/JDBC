package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
	//field
	public int AuthorId;
	public String AuthorName;
	public String AuthorDesc;
	
	//constructor
	
	public AuthorDao() {
		
	}//default constructor

	//method gs
	
	//method
	public void authorInsert(String authorName, String authorDesc) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("success");

		    // 3. SQL문 준비 / 바인딩 / 실행
			String query= "";
			query += " insert into author ";
			query += " values(seq_author_id.nextval, ?, ?) ";
			System.out.println(query);
			
			//문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩
			pstmt.setString(1, authorName);
			pstmt.setString(2, authorDesc);
		    
			//실행
			int count = pstmt.executeUpdate();
			System.out.println(count + "건이 저장되었습니다");
		    // 4.결과처리

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {          
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}
	}//insert
	
	public void authorDelete(int index) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
				
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			//1,2번은 보통 세팅
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("success");
			
			//3. SQL문 준비 / 바인딩 / 실행
			//문자열 만들기
			String query = "";
			query += " delete from author ";
			query += " where author_id = ? ";
			System.out.println(query);
			
			//문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩 (생략)
			pstmt.setInt(1, index);
			
			//실행
			int count = pstmt.executeUpdate();
			System.out.println(count + "건이 삭제되었습니다");
			
			//4. 결과처리
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {          
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }
		}
	}//delete
	
	public void authorUpdate(int index, String authorName, String authorDesc) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3.SQL문 준비 / 바인딩 / 실행
			//문자열 만들기
			String query = "";
			query += " update author ";
			query += " set author_name = ?, ";
			query += "     author_desc = ? ";
			query += " where author_id = ? ";
			System.out.println(query);
			
			//문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩
			pstmt.setString(1, authorName);
			pstmt.setString(2, authorDesc);
			pstmt.setInt(3, index);
			
			//실행
			int count = pstmt.executeUpdate();
			
			//4.결과처리
			System.out.println(count + "건이 수정되었습니다");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}
	//update
	public List<AuthorVo> authorSelect() {
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			  //문자열 만들기
			String query ="";
			query += " select author_id, ";
			query += "        author_name, ";
			query += "        author_desc ";
			query += " from author ";
			System.out.println(query);
			
			//문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩 -->생략  ?표 없음
			
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				int authorId= rs.getInt("author_id");    
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");

				AuthorVo vo= new AuthorVo(authorId, authorName, authorDesc);
				authorList.add(vo);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}return authorList;
	}

}
