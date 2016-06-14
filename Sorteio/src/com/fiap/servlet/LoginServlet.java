package com.fiap.servlet;

import java.io.IOException;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiap.dao.GenericDao;
import com.fiap.entity.User;
import com.fiap.util.Validation;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(false).invalidate();
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String cpf = request.getParameter("cpf");

		if(Validation.containData(password, cpf)){
			try {
				GenericDao<User> dao = new GenericDao<>(User.class);
				User user = dao.login(cpf, password);
				HttpSession session = request.getSession();
				user.setFriendId(new User());
				session.setAttribute("user", user);
				request.getRequestDispatcher("sorteio.jsp").forward(request, response);
				
			

			} catch (NoResultException e){
				e.printStackTrace();
				request.setAttribute("errorMsg", "Usu�rio ou senha incorreto!");
				request.getRequestDispatcher("login.jsp").forward(request, response);

			}catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", "Ocorreu um erro na autenticação!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("errorMsg", "Favor preencher todos os campos");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
