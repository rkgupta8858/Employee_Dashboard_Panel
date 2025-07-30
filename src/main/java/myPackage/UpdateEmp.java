package myPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateEmp extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String driverName = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/employee";
		String userName = "root";
		String pass = "root";
		String sql = "update emp set EmpSalary=?, EmpName=? where EmpId=?";
		
		Integer empId = Integer.parseInt(req.getParameter("EmpId"));
		String empName = req.getParameter("EmpName");
		Double empSalary = Double.parseDouble(req.getParameter("EmpSalary"));
		
		try {
			Class.forName(driverName);
			Connection conn = DriverManager.getConnection(url,userName,pass);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, empSalary);
			ps.setString(2, empName);
			ps.setInt(3, empId);
			Integer count = ps.executeUpdate();
			
			out.println("<html>");
			out.println("<head>");
				out.println("<style>");
					out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; color: #333; padding: 20px; text-align: center; }");
					out.println(".container { background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); width: 400px; margin: auto; }");
					out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
					out.println("td { padding: 10px; text-align: center; font-size: 18px; }");
					out.println("a { text-decoration: none; color: #4CAF50; font-weight: bold; }");
					out.println("a:hover { color: #388E3C; }");
				out.println("</style>");
			out.println("</head>");
			out.println("<body>");
				out.println("<div class='container'>");
				out.println("<table>");
					out.println("<tr>");
						out.println("<td colspan='2'><h1>" + count + " Record Updated</h1></td>");
					out.println("</tr>");
					out.println("<tr>");
						out.println("<td><a href='updateEmp.html'>Update More Record</a></td>");
					out.println("</tr>");
					out.println("<tr>");
						out.println("<td><a href='index.html'>Home</a></td>");
					out.println("</tr>");
				out.println("</table>");
				out.println("</div>");
			out.println("</body>");
		out.println("</html>");
		}
		
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
}
