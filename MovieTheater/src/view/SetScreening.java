package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Control;
import model.User;

/**
 * Servlet implementation class SetScreening
 */
@WebServlet("/SetScreening")
public class SetScreening extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetScreening() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String date = request.getParameter("date").toString();
        String time = request.getParameter("time").toString();
        int movie = Integer.parseInt(request.getParameter("movie"));
        HttpSession session = request.getSession();
        User u = (User)session.getAttribute("User");
		
       	if(Control.addNewScreen(u.movie.elementAt(movie),date,time,u)==1) { //movie already exists      		
       		out.print("<script>\r\n" +"	alert(\"Date and time is unavailable\");\r\n");
       		out.print("window.location = 'http://localhost:8080/MovieTheater/setscreenings.jsp'; "+"</script>");
       		out.close();
       	}
       	else {
       		out.print("<script>\r\n" +"	alert(\"Screening added successfully\");\r\n");
       		out.print("window.location = 'http://localhost:8080/MovieTheater/setscreenings.jsp'; "+"</script>");
       		out.close();
       	}
        out.close();
	}

}
