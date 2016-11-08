package com.xylugah.server.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.xylugah.server.util.IPAddressValidator;
import com.xylugah.springcore.dao.DataDAO;

public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataDAO dao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ip = request.getParameter("ip");
		String port = request.getParameter("port");
		dao.add("ip", 111);
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

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ac = (ApplicationContext) config.getServletContext()
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		this.dao = (DataDAO) ac.getBean("DataDAO");
	}

}
