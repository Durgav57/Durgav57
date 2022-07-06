import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Home {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Welcome...");
		System.out.println("Login");
		System.out.println("1 for admin 2 for customer");
		int n=sc.nextInt();
		try {
		switch(n)
		{
		case 1:
		
			System.out.println("Enter admin name");
			String adname=sc.next();
			System.out.println("Enter password");
			String adpsw=sc.next();
			if(adname.equals("durgav") && adpsw.equals("1234567"))
			{
				System.out.println("Logged in successfully,..");
			}
			else
			{
				System.out.println("Invalid name or password...");
			}
			break;
	
		case 2:
			System.out.println("1 for New user 2 for Registered user,... ");
			int m=sc.nextInt();

					String dburl="jdbc:mysql://localhost:3306/db";
					String dbuser="root";
					String psw="";
					Connection con=DriverManager.getConnection(dburl,dbuser,psw);
					Statement st=con.createStatement();
					if(m==1)
					{
					System.out.println("enter user id...");
					String uid=sc.next();
					System.out.println("enter user name");
					String uname=sc.next();
					System.out.println("enter user age");
					int age=sc.nextInt();
					System.out.println("enter password");
					String upsw=sc.next();
					String query="insert into data values(?,?,?,?)";
					PreparedStatement pst=con.prepareStatement(query);
					pst.setString(1,uid);
					pst.setString(2,uname);
					pst.setInt(3,age);
					pst.setString(4,upsw);
					int d=pst.executeUpdate();
					if(d==1)
					{
						System.out.println("Registered succcessfully...");
					}
					else {
						System.out.println("error");
					}
				
			
				}
		
				
				
			else
			{
				System.out.println("Enter username");
				String name=sc.next();
				System.out.println("Enter password");
				String psws=sc.next();
				String q="select * from data where uname=?";
				PreparedStatement ps=con.prepareStatement(q);
				ps.setString(1, name);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					String nam=rs.getString(2);
					String pd=rs.getString(4);
					if(nam.equals(name) && pd.equals(psws))
					{
						System.out.println("Logged succcessfully...");
					}
					else
					{
						System.out.println("Invalid...");
					}
				}

			}
		}
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
 

	}
}
			
		

