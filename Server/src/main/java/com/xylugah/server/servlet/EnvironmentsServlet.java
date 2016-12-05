package com.xylugah.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xylugah.springcore.dao.DataDAO;
import com.xylugah.springcore.entity.Client;
import com.xylugah.springcore.messages.EnvironmentsRequest;
import com.xylugah.springcore.messages.Response;
import com.xylugah.springcore.model.Action;
import com.xylugah.springcore.transport.Transport;

public class EnvironmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(EnvironmentsServlet.class);
	private DataDAO<Client> dao;
	private Transport transport;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (id != null) {
			try {
				int idClient = Integer.parseInt(id);
				Client client = dao.getById(idClient);
				if (client != null) {
					printClient(client, out);
				} else {
					out.println("<h2>Client with id=" + idClient + " not found!</h2>");
					logger.error("Client with id=" + idClient + " not found!");
				}
			} catch (Exception e) {
				out.println("<h2>No valid id</h2>");
				logger.error("No valid id - " + id, e);
			}
		} else {
			List<Client> clientList = dao.getAll();
			for (Client client : clientList) {
				printClient(client, out);
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
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		this.dao = (DataDAO) ac.getBean("DataDAO");
		this.transport = (Transport) ac.getBean("Transport");

	}

	public void printClient(final Client client, final PrintWriter out) {
		out.println("<h3> id: " + client.getId() + "</h3>");
		out.println("<h3> ip-adress: " + client.getIp() + "</h3>");
		out.println("<h3> port: " + client.getPort() + "</h3>");
		try (Socket socket = new Socket(client.getIp(), client.getPort())) {
			transport.transmitRequest(new EnvironmentsRequest(Action.GET_STATISTICS), socket);
			Response res = transport.receiveResponse(socket);
			socket.close();
			if (res != null) {
				out.println("<h4> Free disk space: " + res.getFreeDiskSpace() + "</h4>");
				out.println("<h4> Free memory: " + res.getFreeMemory() + "</h4>");
				List<String> procList = res.getProcessesList();
				if (procList != null) {
					out.println("<table>");
					for (String proc : procList) {
						StringTokenizer st = new StringTokenizer(proc, " \t\n\r,.");
						out.println("<tr>");
						while (st.hasMoreTokens()) {
							out.println("<td>" + st.nextToken() + "</td>");
						}
						out.println("</tr>");
					}
					out.println("</table>");
				} else {
					out.println("<h2>Proccess list is empty!</h2>");
				}
			} else {
				out.println("<h2>No response from client!</h2>");
				logger.error("Response from client is null!!!");
			}
		} catch (IOException e) {
			out.println("<h2>Connection failed!!!</h2>");
			logger.error("Connection failed with client " + client.getIp() + ":" + client.getPort());
		}
		out.println("<hr></hr>");
	}
}
