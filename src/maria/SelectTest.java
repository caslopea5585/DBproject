/*
 * mariadb�� �����Ͽ� ���ڵ带 �ֿܼ� ����!
 * ����) DBMS �����簡 �����ϴ� ����̹��� �̸� �غ�����!
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
	Connection con; //���������� ���� �������̽�.
	PreparedStatement pstmt; //���� ���ఴü
	ResultSet rs; //���� ������ ��ȯ�Ǵ� ���� �޴� ��ü...
	
	
	/*
	 * 1. ����̹� �ε�
	 * 2. ����
	 * 3. ������ ������
	 * 4. db���ð�ü �ݱ�
	 * */
	public SelectTest() {
		//1. ����̹��� �ε�
		try {
			Class.forName(driver);
			System.out.println("�ε强��");
			
			//2.����
			con = DriverManager.getConnection(url,user,password);
			if(con!=null){
				System.out.println("���Ӽ���");
				//3.���ϴ� ������ ������!!
				String sql="select * from member";
				pstmt = con.prepareStatement(sql);
				rs= pstmt.executeQuery(); 
				//�������� ����Ʈ���ϰ�� �������� �����ͺ��̽��� ���̺�� ������ ��������� ��Ƴ��� ��ü(=ǥ�Ͱ���)
				
				//Ŀ����ĭ ����
				while(rs.next()){
					String name = rs.getString("name"); //�÷��� �ش��ϴ� �� ������
					int age = rs.getInt("age"); //���� ��ȯ
					int member_id=rs.getInt("member_id");
					System.out.println(member_id+","+name+","+age);
				}
				
				
			}else{
				System.out.println("���� ����");
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}finally{	
			//4.db�����ڿ� �ݱ�
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
