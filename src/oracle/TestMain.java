/*
 *  �츮�� ������� �����ͺ��̽� ��ǰ�� ��� DBMS�̴�!!
 *  DB(����)MS(�����ý���) - ��Ʈ��ũ ���� ���
 *  ���������� �����ϴ�.
 *  
 *  ���� ������� ��Ʈ��ũ ���������� TCP/IP��� 
 *  ������ ȣ��Ʈ�� �����Ϸ��� �� ȣ��Ʈ�� �ּҸ� �˾ƾ� �ϴµ�
 *  ����� TCP/IP������ IP�ּҸ� �˾ƾ� �Ѵ�.
 *  user ������������ �˾ƾ� �Ѵ�!!
 *    
 *    jar������ ����ؾ���..
 *    ������Ʈ -> �������콺 -> �����佺> ���ǽ��н� -> ADD libr~ -> user libr => user���̺귯����~
 *    
 * */

package oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestMain {
	public static void main(String[] args) {
/*		1�ܰ� 
 * 			 ����Ŭ�� �ڹٰ� ������ �� �ִ� �ڵ尡 ����ִ� jar������ �޸𸮿� �ε�����.
			 �̷� �����ͺ��̽��� ���� jar������ �ڹٿ����� ����̹��� �Ѵ�.
			 ����̹��� db�����翡�� �����Ѵ�.ex) oracle->����Ŭ��...mysql-->����Ŭ�� mssql ->MS��
*/		
		
		
		//2�ܰ� ����Ŭ�� ��������.
		PreparedStatement pstmt=null;
		Connection con =null;
		//����̹� Ŭ���� �ε� , ��Ʈ�������� ������ ��.. �ְ� ���� jar������ Ŭ�����̸��� �־��ָ� �ε�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �ε� ����!!");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "batman", "1234");
			//����̹� �Ŵ����� ��ȯ���� connection! connection�� �Ǿ�� ������ ������ ����!! ���� �۰��޴ٸ� ���� �ƴϹǷ� ���Ӽ���!!
			if(con != null){
				System.out.println("���Ӽ���");
				
				//���̺� ���� ������ ������ insert�õ�!
				String sql = "insert into company(company_id,brand) values(seq_company.nextval,'����Ű')";
				//������ ������ ���ؼ��� �������� �����ϴ� ��ü�� �̿��ؾ� �Ѵ�.
				//�̰�ü�� �ٷ�!!!!! PreparedStatement
				pstmt=con.prepareStatement(sql);
				int result = pstmt.executeUpdate(); //������ ���� �޼���
				//result�� �������� ���࿡ ���� �ݿ��� ���ڵ��� ���� ��ȯ���ش�
				if(result==1){
					System.out.println("�Է¼���");
				}else{
					System.out.println("�Է½���");
				}
				
			}else{
				System.out.println("���ӽ���");
			}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("����̹��� ã�� �� �����ϴ�.");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//��Ʈ���� DB���� �۾��Ŀ��� �ݵ�� �ݴ� ó���� �ؾ��Ѵ�!!
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
