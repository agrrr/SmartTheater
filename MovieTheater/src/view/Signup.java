package view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Control;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("user_name").toString();
        String password = request.getParameter("password").toString();
        String firstName = request.getParameter("first_name").toString();
       	String lastName = request.getParameter("last_name").toString();
       	String email = request.getParameter("email").toString();
       	String birthday = request.getParameter("birthday").toString();
       	String phone = request.getParameter("phone").toString();
       	
       	if(Control.addNewuser(userName, password,firstName,lastName,email,birthday,phone)==1) { //user already exists      		
       		out.print("<script>\r\n" +"	alert(\"User already exists\");\r\n");
       		out.print("window.location = 'http://localhost:8080/MovieTheater/Signup.jsp'; "+"</script>");
       		out.close();
       	}
       	else {
       		out.print("<script>\r\n" +"	alert(\"User created successfully\");\r\n");
       		out.print("window.location = 'http://localhost:8080/MovieTheater/login.jsp'; "+"</script>");
       		out.close();
       	}
	}
}
