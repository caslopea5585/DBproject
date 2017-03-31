/*
 *  우리가 사용중인 데이터베이스 제품은 모두 DBMS이다!!
 *  DB(저장)MS(관리시스템) - 네트워크 관리 기반
 *  원격접속이 가능하다.
 *  
 *  현재 사용중인 네트워크 프로토콜은 TCP/IP기반 
 *  원격지 호스트를 접속하려면 그 호스트의 주소를 알아야 하는데
 *  기반이 TCP/IP인지라 IP주소를 알아야 한다.
 *  user 계정정보까지 알아야 한다!!
 *    
 *    jar파일을 등록해야함..
 *    프로젝트 -> 우측마우스 -> 빌드페스> 컨피스패스 -> ADD libr~ -> user libr => user라이브러리ㅣ~
 *    
 * */

package oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestMain {
	public static void main(String[] args) {
/*		1단계 
 * 			 오라클을 자바가 제어할 수 있는 코드가 들어있는 jar파일을 메모리에 로드하자.
			 이런 데이터베이스형 제어 jar파일을 자바에서는 드라이버라 한다.
			 드라이버는 db제조사에서 제공한다.ex) oracle->오라클사...mysql-->오라클사 mssql ->MS사
*/		
		
		
		//2단계 오라클에 접속하자.
		PreparedStatement pstmt=null;
		Connection con =null;
		//드라이버 클래스 로드 , 스트링형으로 넣으면 됨.. 넣고 싶은 jar파일의 클래스이름을 넣어주면 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공!!");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "batman", "1234");
			//드라이버 매니저의 반환형은 connection! connection이 되어야 연결이 성공을 간주!! 따라서 송공햇다면 널이 아니므로 접속성공!!
			if(con != null){
				System.out.println("접속성공");
				
				//테이블에 현재 유저가 보유한 insert시도!
				String sql = "insert into company(company_id,brand) values(seq_company.nextval,'나이키')";
				//쿼리문 수행을 위해서는 쿼리문을 전담하는 객체를 이용해야 한다.
				//이객체가 바로!!!!! PreparedStatement
				pstmt=con.prepareStatement(sql);
				int result = pstmt.executeUpdate(); //쿼리문 실행 메서드
				//result는 이쿼리문 수행에 의해 반영된 레코드의 수를 반환해준다
				if(result==1){
					System.out.println("입력성공");
				}else{
					System.out.println("입력실패");
				}
				
			}else{
				System.out.println("접속실패");
			}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//스트림과 DB연동 작업후에는 반드시 닫는 처리를 해야한다!!
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
}
