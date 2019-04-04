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


@WebServlet("/AddMovie")
public class AddMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMovie() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String movieName = request.getParameter("movie_name").toString();
        String genre = request.getParameter("genre").toString();
        String directors = request.getParameter("directors").toString();
       	int year = Integer.parseInt(request.getParameter("year"));
       	String actors = request.getParameter("actors").toString();
       	String review = request.getParameter("review").toString();
       	String trailer = request.getParameter("trailer").toString();
       	String picture = request.getParameter("picture").toString();
    	int length = Integer.parseInt(request.getParameter("length"));
        HttpSession session = request.getSession();
        User u = (User)session.getAttribute("User");
       	
       	if(Control.addNewMovie(u, movieName, genre,directors,year,actors,review,trailer,picture,length)==1) { //movie already exists      		
       		out.print("<script>\r\n" +"	alert(\"Movie already exists\");\r\n");
       		out.print("window.location = 'http://localhost:8080/MovieTheater/addmovie.jsp'; "+"</script>");
       		out.close();
       	}
       	else {
       		out.print("<script>\r\n" +"	alert(\"Movie added successfully\");\r\n");
       		out.print("window.location = 'http://localhost:8080/MovieTheater/addmovie.jsp'; "+"</script>");
       		out.close();
       	}
	}

}
