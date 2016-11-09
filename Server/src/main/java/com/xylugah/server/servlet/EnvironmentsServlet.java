package com.xylugah.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.xylugah.springcore.dao.DataDAO;
import com.xylugah.springcore.model.Client;

public class EnvironmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataDAO dao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		PrintWriter out = response.getWriter();
		if (id == null) {
			List<Client> clientList = dao.getClientList();

			out.println("<html><body>");
			out.println("<h1>List of client</h1>");

			if (clientList.isEmpty()) {
				out.println("<h2>Client list is empty!!!</h2>");
			} else {
				for (Client client : clientList) {
					out.println("<h3> ip-adress: " + client.getIp() + "</h3>");
					out.println("<h3> port: " + client.getPort() + "</h3>");
					out.println("<table>");
					out.println("<tr><td> free memory: " + client.getMemorySize() + "</td></tr>");
					out.println("<tr><td> free disk space: " + client.getDiskSize() + "</td></tr>");
					List<String> proclist = client.getProcessesList();
					if (proclist == null || proclist.isEmpty()) {
						out.println("<tr><td>Process list is empty!!!</td></tr>");
					} else {
						for (String process : proclist) {
							out.println(process);
						}
					}
					out.println("</table>");

				}
			}
			out.println("</body></html>");
		} else {
			int intId = Integer.valueOf(id);
			Client client = dao.getById(intId);
			if (client == null) {
				out.println("<h2>Client with ID=" + intId + " not found!!!</h2>");
			} else {
				out.println("<h3> ip-adress: " + client.getIp() + "</h3>");
				out.println("<h3> port: " + client.getPort() + "</h3>");
				out.println("<table>");
				out.println("<tr><td> free memory: " + client.getMemorySize() + "</td></tr>");
				out.println("<tr><td> free disk space: " + client.getDiskSize() + "</td></tr>");
				List<String> proclist = client.getProcessesList();
				if (proclist == null || proclist.isEmpty()) {
					out.println("<tr><td>Process list is empty!!!</td></tr>");
				} else {
					for (String process : proclist) {
						out.println(process);
					}
				}
				out.println("</table>");
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
