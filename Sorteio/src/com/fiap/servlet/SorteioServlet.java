package com.fiap.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
@WebServlet("/sorteio")
public class SorteioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public SorteioServlet() {
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession(false).getAttribute("user");
		String cpf = (user).getCpf();
		GenericDao<User> dao = new GenericDao<>(User.class);
		List<User> users = dao.getAll();
		List<User> newUsers = users.stream().filter(u -> !u.getCpf().equals(cpf))
				.collect(Collectors.toCollection(ArrayList::new));	
		while(newUsers.isEmpty()){		
			Collections.shuffle(newUsers, new Random(System.nanoTime()));
			User userChosen = newUsers.get(0);
			if(Validation.hasAnotherFriendChosen(newUsers, userChosen)){
				newUsers.remove(0);
				continue;
			}
			else{
				user.setFriendId(userChosen);
				break;
			}			
		}
		
		if(newUsers.isEmpty()){
			request.setAttribute("msgError", "Ops, todos os amiguinhos já foram escolhidos!");
			request.getRequestDispatcher("sorteio.jsp").forward(request, response);
		}
		
		try {
			dao.update(user);
			request.setAttribute("user.friendId.name", user.getFriendId().getName());
			request.getRequestDispatcher("sorteio.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		/*
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
		}*/

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
