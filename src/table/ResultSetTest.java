/*
 * 레코드 결과를 배열로 받을때의 단점
 * 레코드의 총 갯수를 알수가 없었다...
 * */
package table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Java Data Base Connectivity = 자바의 데이터 연동기술
public class ResultSetTest {
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String id="batman";
	String password="1234";
	
	Connection con; //접속한 이후 그 결과를 담는 객체.
	PreparedStatement pstmt;
	ResultSet rs;
	
	//레코드셋 객체를 이용하여 총 레코드수 알아맞춰보기.
	public ResultSetTest() {
		try {
			Class.forName(driver);
			con =DriverManager.getConnection(url,id,password);
			
			if(con!=null){
				String sql = "select * from company";
				//rs의 커서를 전방향,부방향등으로 자유롭게 움직이거나, 한꺼번에 건너뛰게 하려면 스크롤 가능한
				//상수옵션을 부여해야 한다.
				//select문의 결과집합을 대상으로 단지 보기만 할경우에는 Read_ONly로 가면된다..
				//									수정을 가할거라면 UPDATABLE
				//강사님 경험상 select문에 레코드는 읽기위함이다!!
				//스크롤 인센티브가 위아래로 움직일수 있게한거임....
				pstmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs=pstmt.executeQuery();
			
				//제일마지막 레코드로 보내기
				rs.last();
				int num = rs.getRow(); //현재 커서가 가리키는 레코드 번호, 즉 레코드의 위치!!
				System.out.println(num);
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		new ResultSetTest();
	}
}