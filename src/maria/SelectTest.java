/*
 * mariadb를 연동하여 레코드를 콘솔에 찍어보자!
 * 주의) DBMS 제조사가 제공하는 드라이버를 미리 준비하자!
 * */

package maria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest {
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/db0331";
	String user="root";
	String password="";
	Connection con; //접속정보를 가진 인터페이스.
	PreparedStatement pstmt; //쿼리 수행객체
	ResultSet rs; //쿼리 수행후 반환되는 값을 받는 객체...
	
	
	/*
	 * 1. 드라이버 로드
	 * 2. 접속
	 * 3. 쿼리문 날리기
	 * 4. db관련객체 닫기
	 * */
	public SelectTest() {
		//1. 드라이버를 로드
		try {
			Class.forName(driver);
			System.out.println("로드성공");
			
			//2.접속
			con = DriverManager.getConnection(url,user,password);
			if(con!=null){
				System.out.println("접속성공");
				//3.원하는 쿼리문 날리기!!
				String sql="select * from member";
				pstmt = con.prepareStatement(sql);
				rs= pstmt.executeQuery(); 
				//쿼리문이 셀럭트문일경우 원격지의 데이터베이스의 테이블과 동일한 결과집합을 담아놓는 객체(=표와같다)
				
				//커서한칸 전진
				while(rs.next()){
					String name = rs.getString("name"); //컬럼에 해당하는 값 가져옴
					int age = rs.getInt("age"); //나이 반환
					int member_id=rs.getInt("member_id");
					System.out.println(member_id+","+name+","+age);
				}
				
				
			}else{
				System.out.println("접속 실패");
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}finally{	
			//4.db관련자원 닫기
			try {
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(pstmt!=null){
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(con!=null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
	
	
	}
	
	public static void main(String[] args) {
		new SelectTest();
	}
}
