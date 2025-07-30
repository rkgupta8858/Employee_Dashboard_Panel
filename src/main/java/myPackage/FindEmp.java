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
 
public class FindEmp extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String driverName = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/employee";
		String userName = "root";
		String pass = "root";
		String sql = "select * from emp where EmpId=?";
		
		Integer empId = Integer.parseInt(req.getParameter("EmpId"));
		
		try {
			Class.forName(driverName);
			Connection connection = DriverManager.getConnection(url,userName,pass);
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, empId);
			
			
			ResultSet resultSet = ps.executeQuery();
			
			if (resultSet.next()) {
			    out.println("<table style='border: 2px solid #4CAF50; border-collapse: collapse; margin: 20px auto; width: 80%; font-family: Arial, sans-serif;'>");
			    
			    // Table Header with Colors
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
			    do {
			        String rowColor = (rowCounter % 2 == 0) ? "#f9f9f9" : "#e0f7fa"; // Alternating row colors
			        out.println("<tr style='background-color: " + rowColor + "; text-align: center;'>");
			        out.println("<td style='padding: 10px; border: 1px solid #ddd;'>" + resultSet.getInt(1) + "</td>");
			        out.println("<td style='padding: 10px; border: 1px solid #ddd;'>" + resultSet.getString(2) + "</td>");
			        out.println("<td style='padding: 10px; border: 1px solid #ddd;'>" + resultSet.getDouble(3) + "</td>");
			        out.println("</tr>");
			        rowCounter++;
			    } while (resultSet.next());
			    
			    out.println("</tbody>");
			    out.println("</table>");
			} else {
			    out.println("<h1 style='text-align: center; color: red; font-family: Arial, sans-serif;'>Record Not Found</h1>");
			}
			
			out.println("<a href='findEmp.html' style='"
				    + "display: inline-block; "
				    + "margin-left: 138px; "
				    + "padding: 10px 20px; "
				    + "text-decoration: none; "
				    + "color: white; "
				    + "background-color: #4CAF50; "
				    + "border-radius: 5px; "
				    + "font-family: Arial, sans-serif; "
				    + "font-size: 16px; "
				    + "box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);"
				    + "'>"
				    + "Find Another Employee Data"
				    + "</a> <br>");
			
			out.println("<a href='index.html' style='"
				    + "display: inline-block; "
				    + "margin-left: 138px; "
				    + "margin-top: 20px;"
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
			
			catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		
		
		
	}
}














