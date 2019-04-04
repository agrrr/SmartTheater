package view;
import controller.Control;
import model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(description = "login page", urlPatterns = { "/Login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("userName").toString();
        String password = request.getParameter("password").toString();       	
        if(Control.isValid(userName, password)) {
        	User u = Control.setUser(userName);
        	u.creatLocaldb();
        	HttpSession session = request.getSession();
        	session.setAttribute("login", 1);
            if(userName.equals("admin")) {
            	u.creatManagerdb();
            	u.algoPart1();
            	u.algoPart2();
            	session.setAttribute("User", u);
    			String nextHtml = response.encodeRedirectURL("homemanager.jsp");
    			response.sendRedirect(nextHtml);
            	out.close();
            }
            else {
            	u.creatPersonaldb();
            	u.algoPart1();
            	u.algoPart2();
            	session.setAttribute("User", u);
    			String nextHtml = response.encodeRedirectURL("homepage.jsp");
    			response.sendRedirect(nextHtml);
            	out.close();
            }
        }
        else {
       		out.print("<script>\r\n" +"	alert(\"Incorrect User name or Password\");\r\n");
       		out.print("window.location = 'http://localhost:8080/MovieTheater/login.jsp'; "+"</script>");
       		out.close();
        }
	}
}
