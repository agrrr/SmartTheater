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


@WebServlet("/InfoUpdate")
public class InfoUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public InfoUpdate() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String newPsw = request.getParameter("new_password").toString();
        String rePsw = request.getParameter("re_password").toString();
        HttpSession session = request.getSession();
        String userName = (String)session.getAttribute("userName");
        if(newPsw.equals(rePsw)) {
        	Control.updateInfo(userName, newPsw);
       		out.print("<script>\r\n" +"	alert(\"Password changed\");\r\n");
       		out.print("window.location = 'http://localhost:8080/MovieTheater/AccInfo.jsp'; "+"</script>");
        	out.close();
        }
        else {
       		out.print("<script>\r\n" +"	alert(\"Password doesn't match\");\r\n");
       		out.print("window.location = 'http://localhost:8080/MovieTheater/AccInfo.jsp'; "+"</script>");
       		out.close();
        }
	}

}
