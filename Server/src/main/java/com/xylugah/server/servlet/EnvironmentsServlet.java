package com.xylugah.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xylugah.springcore.dao.DataDAO;
import com.xylugah.springcore.model.GetStatisticsRequest;
import com.xylugah.springcore.model.Response;
import com.xylugah.springcore.transport.Transport;

public class EnvironmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(EnvironmentsServlet.class);
	private DataDAO dao;
	private Transport transport;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("In EnvironmentsServlet");
		Socket socket = new Socket("127.0.0.1", 1238);
		transport.transmit(new GetStatisticsRequest(), socket);
		Response res = (Response) transport.receive(socket);
		PrintWriter out = response.getWriter();
		out.println(res.getFreeDiskSpace());
		socket.close();
		/*
		 * String id = request.getParameter("id"); PrintWriter out =
		 * response.getWriter(); if (id == null) { List<Client> clientList =
		 * dao.getClientList();
		 * 
		 * out.println("<html><body>"); out.println("<h1>List of client</h1>");
		 * 
		 * if (clientList.isEmpty()) {
		 * out.println("<h2>Client list is empty!!!</h2>"); } else { for (Client
		 * client : clientList) { out.println("<h3> id: " + client.getId() +
		 * "</h3>"); out.println("<h3> ip-adress: " + client.getIp() + "</h3>");
		 * out.println("<h3> port: " + client.getPort() + "</h3>");
		 * out.println("<table>"); out.println("</table>");
		 * 
		 * } } out.println("</body></html>"); } else { int intId =
		 * Integer.valueOf(id); logger.info("Get user by id = " + intId); Client
		 * client = dao.getById(intId); if (client == null) {
		 * out.println("<h2>Client with ID=" + intId + " not found!!!</h2>"); }
		 * else { out.println("<h3> ip-adress: " + client.getIp() + "</h3>");
		 * out.println("<h3> port: " + client.getPort() + "</h3>");
		 * out.println("<table>"); out.println("</table>"); }
		 * 
		 * } out.close();
		 */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// ApplicationContext ac = (ApplicationContext)
		// config.getServletContext()
		// .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		// ApplicationContext ac =
		// WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		// ApplicationContext ac =
		// WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		this.dao = (DataDAO) ac.getBean("DataDAO");
		this.transport = (Transport) ac.getBean("Transport");

	}
}
