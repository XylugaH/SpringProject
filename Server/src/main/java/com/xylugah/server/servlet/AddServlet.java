package com.xylugah.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xylugah.server.util.IPAddressValidator;

public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ip = request.getParameter("ip");
		String port = request.getParameter("port");
		if (ip == null || port == null) {

		}
		IPAddressValidator ipValidator = new IPAddressValidator();
		if (!ipValidator.validate(ip)) {

		}
		// Integer id = (Integer) request.getParameter("port");
		response.getWriter().append(ip + port + "ADD: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
