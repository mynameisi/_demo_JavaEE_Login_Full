package come2niks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns="/LoginServlet")
public class LoginServlet extends HttpServlet {
	private final static Logger looger=LoggerFactory.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			looger.debug("start doGet method");
			UserBean user = new UserBean();
			user.setUserName(request.getParameter("uname"));
			user.setPassword(request.getParameter("password"));
			user = LoginDAO.login(user);
			if(user.isValid())
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser",user);
				response.sendRedirect("LoginSuccess.jsp");
			}else
				response.sendRedirect("LoginFailed.jsp");
		} catch (Throwable exc)
		{
			System.out.println(exc);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}