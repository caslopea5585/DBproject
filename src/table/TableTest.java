/*
 * swing�� ������Ʈ �� �����ͺ��̽��� ��� ������ �ð�ȭ�ϱ⿡ ����ȭ�� ������Ʈ�� �ִµ�
 * JTable�̴�!!!
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
	ResultSet rs; //select���� ��츸 �ʿ���!! ��??����� ��ƾ� �ϹǷ�
	
	
	
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
	//���ڵ� ä���ֱ�
	//���̺��� �����ϱ� ���� mariadb�����Ͽ� member���̺��� ���ڵ带 �������迭�� ��Ƴ���!
	//��?? JTable�� �������� �μ��� ������ �迭�� ���Ǵϱ�!!
	public void loadData(){
		/*
		 * 1.����̹��ε�
		 * 2.����
		 * 3.������������
		 * 4.���ݱ� 
		 * */
		int index=0;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			
			
			if(con!=null){
				String sql= "select * from member";
				pstmt =con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				//�������迭 ����
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
