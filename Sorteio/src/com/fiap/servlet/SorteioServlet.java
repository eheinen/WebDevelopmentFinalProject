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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GenericDao<User> dao = new GenericDao<>(User.class);
		User user = dao.findById(((User) request.getSession(false).getAttribute("user")).getId());
		if (Validation.hasAlreadyShuffle(user)) {
			request.setAttribute("errorMsg", "Você já escolheu o seu amiguinho!");
			request.setAttribute("userFriend", user.getFriendId().getName());
			request.getRequestDispatcher("sorteio.jsp").forward(request, response);
		} else {
			String cpf = (user).getCpf();
			List<User> users = dao.getAll();
			List<User> newUsers = users.stream().filter(u -> !u.getCpf().equals(cpf))
					.collect(Collectors.toCollection(ArrayList::new));
			Collections.shuffle(newUsers);

			for(int i=0; i < newUsers.size(); i++){				
				User userChosen = newUsers.get(i);
				if (!Validation.hasNotAnotherFriendChosen(newUsers, userChosen)) {
					continue;
				} else {
					user.setFriendId(userChosen);
					break;
				}
			}

			if (newUsers.isEmpty()) {
				request.setAttribute("errorMsg", "Ops, todos os amiguinhos já foram escolhidos!");
				request.getRequestDispatcher("sorteio.jsp").forward(request, response);
			} else {
				try {
					dao.update(user);
					request.setAttribute("userFriend", user.getFriendId().getName());
					request.getRequestDispatcher("sorteio.jsp").forward(request, response);
				} catch (Exception e) {
					request.setAttribute("errorMsg", "Ops, todos os amiguinhos já foram escolhidos!");
					request.getRequestDispatcher("sorteio.jsp").forward(request, response);
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
