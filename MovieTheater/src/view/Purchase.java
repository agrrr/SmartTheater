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
import model.Screening;
import model.User;

/**
 * Servlet implementation class Purchase
 */
@WebServlet("/Purchase")
public class Purchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Purchase() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int buy = Integer.parseInt(request.getParameter("buy")),movieId,screeningId;
        HttpSession session = request.getSession();
        User u=(User)session.getAttribute("User");
        Screening s= u.getScreening(buy);
        movieId=s.movie.getId();
        screeningId=s.getId();
        Control.purchase(u.getId(),movieId,screeningId);
   		out.print("<script>\r\n" +"	alert(\"Purchased Successfully\");\r\n");
   		out.print("window.location = 'http://localhost:8080/MovieTheater/homepage.jsp'; "+"</script>");
        out.close();
	}

}
