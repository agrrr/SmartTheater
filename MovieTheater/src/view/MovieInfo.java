package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Movie;
import model.User;

@WebServlet("/MovieInfo")
public class MovieInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MovieInfo() {
        super();
        
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    	int movie = Integer.parseInt(request.getParameter("movie"));
        HttpSession session = request.getSession();
        User u=(User)session.getAttribute("User");
        Movie m= u.getMovie(movie);
        session.setAttribute("movie",m);
		String nextHtml = response.encodeRedirectURL("movieinfo.jsp");
		response.sendRedirect(nextHtml);
		out.close();
	}

}
