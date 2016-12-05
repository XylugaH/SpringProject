package com.xylugah.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.xylugah.springcore.dao.DataDAO;

public class RemoveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(AddServlet.class);
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
			logger.info("No valid id - " + id);
		} else {
			try {
				int idClient = Integer.parseInt(id);
				if (dao.removeById(idClient)) {
					out.println("<h2>Remove successful!!!</h2>");
					logger.info("Remove successful id - " + id);
				} else {
					out.println("<h2>Remove error!!!</h2>");
					logger.info("Remove error id - " + id);
				}
			} catch (Exception e) {
				out.println("<h2>No valid id</h2>");
				logger.error("No valid id - " + id);
			}

		}
		out.close();
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
