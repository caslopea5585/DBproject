/*
 * ���ڵ� ����� �迭�� �������� ����
 * ���ڵ��� �� ������ �˼��� ������...
 * */
package table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Java Data Base Connectivity = �ڹ��� ������ �������
public class ResultSetTest {
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String id="batman";
	String password="1234";
	
	Connection con; //������ ���� �� ����� ��� ��ü.
	PreparedStatement pstmt;
	ResultSet rs;
	
	//���ڵ�� ��ü�� �̿��Ͽ� �� ���ڵ�� �˾Ƹ��纸��.
	public ResultSetTest() {
		try {
			Class.forName(driver);
			con =DriverManager.getConnection(url,id,password);
			
			if(con!=null){
				String sql = "select * from company";
				//rs�� Ŀ���� ������,�ι�������� �����Ӱ� �����̰ų�, �Ѳ����� �ǳʶٰ� �Ϸ��� ��ũ�� ������
				//����ɼ��� �ο��ؾ� �Ѵ�.
				//select���� ��������� ������� ���� ���⸸ �Ұ�쿡�� Read_ONly�� ����ȴ�..
				//									������ ���ҰŶ�� UPDATABLE
				//����� ����� select���� ���ڵ�� �б������̴�!!
				//��ũ�� �μ�Ƽ�갡 ���Ʒ��� �����ϼ� �ְ��Ѱ���....
				pstmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs=pstmt.executeQuery();
			
				//���ϸ����� ���ڵ�� ������
				rs.last();
				int num = rs.getRow(); //���� Ŀ���� ����Ű�� ���ڵ� ��ȣ, �� ���ڵ��� ��ġ!!
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