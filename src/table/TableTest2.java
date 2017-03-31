/*
 * 
 * 레코드의 갯수에따라 배열의 크기를 지정해서 개발해보자!!
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

public class TableTest2 extends JFrame{
	JTable table;
	JScrollPane scroll;
	String driver="oracle.jdbc.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="batman";
	String password="1234";
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs; //select문일 경우만 필요함!! 왜??결과를 담아야 하므로
	
	
	
	String[][] data;
	String[] column={
		"EMPNO","ENAME","job","mgr","hiredate",
		"sal","comm","deptno"			
	};
	
	public TableTest2() {
		
		loadData();
		table = new JTable(data,column);
		scroll = new JScrollPane(table);
		add(scroll);
		
		setSize(800,600);
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
				String sql= "select * from emp";
				pstmt =con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs = pstmt.executeQuery();
				
				//rs를 last()로 보내고 위치를물어보자..
				rs.last();
				int total = rs.getRow();
				//이차원배열 생성
				data= new String[total][column.length];
				
				//rs원상복구
				rs.beforeFirst(); //아무것도 가리키지 않는 상태!!!
				
				while(rs.next()){
					int empno = rs.getInt("empno");
					String ename = rs.getString("ename");
					String job = rs.getString("job");
					int mgr = rs.getInt("mgr");
					String hiredate = rs.getString("hiredate");
					int sal = rs.getInt("sal");
					String comm= rs.getString("comm");
					int deptno = rs.getInt("deptno");
					
					
					data[index][0]=Integer.toString(empno);
					data[index][1]=ename;
					data[index][2]=job;
					data[index][3]=Integer.toString(mgr);
					data[index][4]=hiredate;
					data[index][5]=Integer.toString(sal);
					data[index][6]=comm;
					data[index][7]=Integer.toString(deptno);
					
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
		new TableTest2();
	}
}
