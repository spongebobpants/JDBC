package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*insert, update, delete, select문 너무 기니까 클래스로 묶어주기
 * 클래스로 설계하기 -> 필드
 * 					생성자
 * 					메소드 gs
 * 					메소드 일반 (insert() , update(), delete(), select()
 * -> AuthorDao dao = new AuthorDao();
 * dao.insert("서강준", "배우");
 * dao.update(1, "서강준", "배우, 모델");
 */

public class AuthorInsert {

	public AuthorInsert() {}

	public static void main(String[] args) {

		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
		//1,2번은 보통 세팅
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		// 2. Connection 얻어오기 -localhost 자리 ip 주소입력 / webdb 자리 id pw
		//import -> command+shift+O -> 스태틱 오류 해결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속 성공");
			
		// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; //쿼리'문자열' 만들기-> ? 주의하기(그냥 외우기!!)
			
		//query = query + "insert into author"; 
		//tip 쿼리 에러를 피하기 위해 ""사이를 띄워서 공백을 넣어준다
			query += " insert into author ";
			
		//query = query + "values (seq_author_id.nextval, '이문열', '경북 영양')";
		    query += " values(seq_author_id.nextval, ?, ? ) " ;
		    System.out.println(query);
		//data는 계속 바뀌니까 ?로 표기한다
		//따옴표 양 끝 띄어주기 바로 밑으로 들어가기 때문에 insert into authorvalues...<- 이렇게 된다
			
		//문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);//그냥 외우기..!
					
		//바인딩		
			pstmt.setString(1, "박경리");  // 첫번쨰 물음표의 		
			pstmt.setString(2, "여류 작가");//두번째 물음표의 데이터
			
		//실행
			int count = pstmt.executeUpdate(); //쿼리문 실행
			System.out.println( count + "건이 저장되었습니다.");
			
		// 4.결과처리
		} catch (ClassNotFoundException e) {
		System. out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System. out.println("error:" + e);
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
				System. out.println("error:" + e);
			}
		}
	}
}