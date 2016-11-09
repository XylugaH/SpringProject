package com.xylugah.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.xylugah.springcore.dao.DataDAO;

public class RemoveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataDAO dao;

	public RemoveController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String id = request.getParameter("id");
		PrintWriter out = response.getWriter();

		if (id == null) {
			out.println("<h2>Error: ID is empty!!!</h2>");
		} else {
			int idClient = Integer.valueOf(id);
			if (dao.remove(idClient)) {
				out.println("<h2>Remove successful!!!</h2>");
			} else {
				out.println("<h2>Remove error!!!</h2>");
			}
		}
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
