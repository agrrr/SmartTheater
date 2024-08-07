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

/**
 * Servlet implementation class MovieInfoManager
 */
@WebServlet("/MovieInfoManager")
public class MovieInfoManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieInfoManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    	int movie = Integer.parseInt(request.getParameter("movie"));
        HttpSession session = request.getSession();
        User u=(User)session.getAttribute("User");
        Movie m= u.getMovie(movie);
        session.setAttribute("movie",m);
		String nextHtml = response.encodeRedirectURL("movieinfomanager.jsp");
		response.sendRedirect(nextHtml);
		out.close();
	}

}
