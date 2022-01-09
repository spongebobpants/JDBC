package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorSelect {
	public static void main(String []args) {
		
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		//작가 데이터
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
			System.out.println("접속 성공");

		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select author_id, ";
			query += "        author_name, ";
			query += "        author_desc ";
			query += " from author ";
			System.out.println(query);
			//쉼표 !!!! ;만 빼고 다 가져와야함 !!
			//select문 이런 식으로 물음표 필요 없을 때 있음
			
			//문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩 -> ?표 없으므로 생략
			
			//실행
			rs = pstmt.executeQuery();
			//rs는 select문에 해당하는 큰 덩어리
		    
		    // 4.결과처리
			//for 아니라 while문인 이유 select문 전체 개수 그때그때 모름
			while(rs.next()) {
				//int authorId = rs.getInt("author_id");
				//만약 별명 지정해줬으면 별명만 !! 입력해주기
				//cursor authorId 컬럼명 써주기, 앞에 변수
				/*
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				System.out.println(authorId+","+authorName+","+authorDesc);
				*/
				int authorId = rs.getInt(1);//첫번째 컬럼 꺼내준다
				String authorName = rs.getString(2);
				String authorDesc = rs.getString(3);
				System.out.println(1+","+2+","+3);
				//컬럼 인덱스 숫자대로 하면 순서가 바뀔 우려가 있음
				
				AuthorVo vo = new AuthorVo(authorId, authorName, authorDesc);
				authorList.add(vo);			
				}
			
			//출력
			for(int i=0; i<authorList.size(); i++) {
				AuthorVo authorVo = authorList.get(i);
				System.out.println(authorVo.getAuthorId()+","+authorVo.getAuthorName()+","+authorVo.getAuthorDesc());
			}
			
			//첫번째 작가 다시 출력
			//AuthorVo authorVo = authorList.get(0);
			//System.out.println(authorVo.getAuthorName());

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

		}
	}

}
