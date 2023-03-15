package jdbcBasics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","root1234");
				Statement st=conn.createStatement();
				ResultSet rs=st.executeQuery("select * from account");){
			while(rs.next())
			{
				int accNo=rs.getInt(1);
				String fname=rs.getString(2);
				String lname=rs.getString(3);
				int bal=rs.getInt(4);
				System.out.println(accNo+" | "+fname+" | "+lname+" | "+bal);
			}
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

}
