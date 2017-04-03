package homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

public class Conn extends AbstractTableModel {
	
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/db0331";
	String user="root";
	String password="";
	
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs; 
	
	String[] col = {"deptno","dname","loc"};
	String[][] data;
	int num;
	
	
	public Conn() {
		int index =0;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			
			if(con!=null){
				String sql = "select * from dept";
				
				pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs = pstmt.executeQuery();
				rs.last();
				num = rs.getRow();
				data = new String[num][col.length];
				rs.beforeFirst();
				while(rs.next()){
					int deptno = rs.getInt(col[0]);
					String dname = rs.getString(col[1]);
					String loc = rs.getString(col[2]);
					
					data[index][0] = Integer.toString(deptno);
					data[index][1] = dname;
					data[index][2] = loc;
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
