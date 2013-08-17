package wgz.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wgz.model.LoginDAO;
import wgz.model.UserBean;

@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
	private final static Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("SERVICE 方法启动");
		Enumeration<String> em=this.getServletContext().getAttributeNames();
		for(String s:Collections.list(em)){
			logger.debug(s);
			logger.debug(this.getServletContext().getAttribute("org.apache.catalina.resources").toString());
		}
		try {
			UserBean user = new UserBean();
			user.setUserName(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user = LoginDAO.login(user);
			if (user.isValid()) {
				HttpSession session = request.getSession(true);
				session.setAttribute("user", user.getFirstName());
				response.sendRedirect("LoginSuccess.jsp");
			} else
				response.sendRedirect("LoginFailed.jsp");
		} catch (Throwable exc) {
			System.out.println(exc);
		}
		super.service(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}