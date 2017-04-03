package homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

public class Conn2 extends AbstractTableModel {
	
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/test";
	String user="root";
	String password="";
	
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs; 
	
	String[] col = {"empno","ename","job","mgr","hiredate","sal","comm","deptno"};
	String[][] data;
	int num;
	
	
	public Conn2() {
		int index =0;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			
			if(con!=null){
				String sql = "select * from emp";
				
				pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs = pstmt.executeQuery();
				rs.last();
				num = rs.getRow();
				data = new String[num][col.length];
				rs.beforeFirst();
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
		}
	}

	public int getColumnCount() {

		return col.length;
	}


	public int getRowCount() {

		return num;
	}


	public Object getValueAt(int row, int col) {

		return data[row][col];
	}
	
	
}
