package myPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindAll extends HttpServlet
{
	public void doGet( HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		String driverName="com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://@localhost:3306/employee";
		String username = "root";
		String password = "root";
		String sql = "select * from emp ";
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
	
		try {
			Class.forName(driverName);
			Connection connection = DriverManager.getConnection(url,username,password);
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet resultSet = ps.executeQuery();
				
			// Styled Table
			out.println("<table style='border: 2px solid #4CAF50; border-collapse: collapse; margin: 20px auto; width: 80%; font-family: Arial, sans-serif;'>");

			// Table Header
			out.println("<thead style='background-color: #4CAF50; color: white;'>");
			out.println("<tr>");
			out.println("<th style='padding: 10px; border: 1px solid #ddd;'>EmpId</th>");
			out.println("<th style='padding: 10px; border: 1px solid #ddd;'>EmpName</th>");
			out.println("<th style='padding: 10px; border: 1px solid #ddd;'>EmpSalary</th>");
			out.println("</tr>");
			out.println("</thead>");

			// Table Body with Alternating Row Colors
			out.println("<tbody>");
			int rowCounter = 0;

			while (resultSet.next()) {
			    String rowColor = (rowCounter % 2 == 0) ? "#f9f9f9" : "#e0f7fa"; // Alternating row colors
			    out.println("<tr style='background-color: " + rowColor + "; text-align: center;'>");
			    out.println("<td style='padding: 10px; border: 1px solid #ddd;'>" + resultSet.getInt(1) + "</td>");
			    out.println("<td style='padding: 10px; border: 1px solid #ddd;'>" + resultSet.getString(2) + "</td>");
			    out.println("<td style='padding: 10px; border: 1px solid #ddd;'>" + resultSet.getDouble(3) + "</td>");
			    out.println("</tr>");
			    rowCounter++;
			}

			out.println("</tbody>");
			out.println("</table>");

			// Styled Home Link
			out.println("<a href='index.html' style='"
			        + "display: inline-block; "
			        + "margin: 20px auto; "
			        + "padding: 10px 20px; "
			        + "text-decoration: none; "
			        + "color: white; "
			        + "background-color: #4CAF50; "
			        + "border-radius: 5px; "
			        + "font-family: Arial, sans-serif; "
			        + "font-size: 16px; "
			        + "box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);"
			        + "'>"
			        + "Home"
			        + "</a>");

					
		}
		catch(ClassNotFoundException e) {
			out.println(e);
		}
		catch(SQLException e) {
			out.println(e);
		}
		
	}
}
