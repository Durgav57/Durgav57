package customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Purchase {
	public void getProductInfo()
	{
	try{
		Scanner sc=new Scanner(System.in);
		String dburl="jdbc:mysql://localhost:3306/db";
		String dbuser="root";
		String psw="";
		Connection con=DriverManager.getConnection(dburl,dbuser,psw);
		Statement st=con.createStatement();
		System.out.println("enter Product name...");
		String pn=sc.next();
		System.out.println("No of products do u want...");
		int n=sc.nextInt();
		String query="select * from productdetail where pname=?";
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, pn);
		ResultSet rs=pst.executeQuery();
		if(rs.next())
		{
		System.out.println("Product exist...");
		int id=rs.getInt(1);
		String pname=rs.getString(2);
		String brand=rs.getString(3);
		int price=rs.getInt(4);
		int stock=rs.getInt(5);
		System.out.println(id+" "+pname+" "+brand+" "+price+" "+stock);	
		if(stock>=n)
		{
			System.out.println("product available");
			int amount=price*n;
			System.out.println("Amount:"+amount);
			int s=stock-n;
			String updates="update productdetail set stock=? where pname=?";
			PreparedStatement ps=con.prepareStatement(updates);
			ps.setInt(1, s);
			ps.setString(2, pname);
			ps.executeUpdate();
		}
		else {
			System.out.println("Out of stock");
		}
		}
		else {	
				System.out.println("Product doesn't exist...");
				System.out.println("Available Products...");
				String q="select * from productdetail";
				PreparedStatement p=con.prepareStatement(q);
				ResultSet s=p.executeQuery();
				while(s.next())
				{
				String pname=s.getString(2);
				System.out.println(pname);	
				}
				
			}
	}
		catch(SQLException e)
		{
			System.out.println(e);
		}
 
}
}
