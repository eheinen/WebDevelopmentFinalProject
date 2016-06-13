package com.fiap.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiap.dao.GenericDao;
import com.fiap.entity.User;
import com.fiap.util.Validation;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public UserServlet() {
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//boolean admin = request.getParameter("admin") == null ? false : true;
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String cpf = request.getParameter("cpf");

		if(Validation.containData(password, name, cpf)){
			User user = new User();
			try {
				user.setPassword(password);
				user.setName(name);
				user.setCpf(cpf);

				GenericDao<User> dao = new GenericDao<>(User.class);
				dao.persist(user);
				request.setAttribute("msg", "O usuário "+user.getName()+ " foi incluido com sucesso!");

			} catch (Exception e) {
				request.setAttribute("user", user);
				request.setAttribute("msgError", "Desculpa não foi possivel efetuar o cadastro. Problemas com a conexão.");
				e.printStackTrace();
			}finally{
				request.getRequestDispatcher("registerUser.jsp").forward(request, response);
			}

		}else {
			request.setAttribute("msgError", "Favor preencher todos os campos");
			request.getRequestDispatcher("registerUser.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
