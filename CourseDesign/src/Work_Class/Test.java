package Work_Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {

	public void main(String[] args) {
		try {
			Class.forName("jdbc");
			Connection conn=DriverManager.getConnection("jdbc:");
			Statement stmt=null;
			PreparedStatement pstmt=null;
			stmt=conn.createStatement();
			String sql="  ";
			ResultSet rs=stmt.executeQuery(sql);
			pstmt=conn.prepareStatement(sql);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			

	}
}
