/*
 * swing의 컴포넌트 중 데이터베이스의 결과 집합을 시각화하기에 최적화된 컴포넌트가 있는데
 * JTable이다!!!
 * 
 * */

package table;

import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableTest extends JFrame{
	JTable table;
	JScrollPane scroll;
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/db0331";
	String user="root";
	String password="";
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs; //select문일 경우만 필요함!! 왜??결과를 담아야 하므로
	
	
	
	String[][] data;
	String[] column={"member_id","name","age"};
	
	public TableTest() {
		
		loadData();
		table = new JTable(data,column);
		scroll = new JScrollPane(table);
		add(scroll);
		
		setSize(500,150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	//레코드 채워넣기
	//테이블을 생성하기 전에 mariadb연동하여 member테이블의 레코드를 이차원배열에 담아놓자!
	//왜?? JTable의 생성자의 인수로 이차원 배열이 사용되니깐!!
	public void loadData(){
		/*
		 * 1.드라이버로드
		 * 2.접속
		 * 3.쿼리문날리기
		 * 4.문닫기 
		 * */
		int index=0;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			
			
			if(con!=null){
				String sql= "select * from member";
				pstmt =con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				//이차원배열 생성
				data= new String[3][3];
				
				while(rs.next()){
						int member_id=rs.getInt(column[0]);
						String name=rs.getString(column[1]);
						int age = rs.getInt(column[2]);
						
						data[index][0]=Integer.toString(member_id);
						data[index][1]=name;
						data[index][2]=Integer.toString(age);
						index++;			
					
				}
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
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
		new TableTest();
	}
}
