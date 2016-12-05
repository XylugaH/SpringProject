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

import com.xylugah.server.util.IPAddressValidator;
import com.xylugah.server.util.PortValidator;
import com.xylugah.springcore.dao.DataDAO;
import com.xylugah.springcore.entity.Client;

public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(AddServlet.class);
	private DataDAO<Client> dao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ip = request.getParameter("ip");
		String port = request.getParameter("port");
		PrintWriter out = response.getWriter();
		if (ip == null || port == null) {
			out.println("<h2>No such requered parameters!</h2>");
			out.println("Request sould be like /environments/add?ip=xxx.xxx.xxx.xxx&port=xxxx(1024...49151)");
			logger.info("No such requered parameters!" + "ip =" + ip + " port =" + port);
		} else {
			IPAddressValidator ipValidator = new IPAddressValidator();
			if (!ipValidator.validate(ip)) {
				out.println("<h2>No valid ip address</h2>");
				logger.info("No valid ip address - " + ip);
			} else {
				try {
					int portInt = Integer.parseInt(port);
					if (!PortValidator.validPortValue(portInt)) {
						out.println("<h2>No valid port value. Value must be 1024...49151</h2>");
						logger.info("No valid port value - " + port);
					} else {
						Client client = new Client(ip, portInt, 0);
						dao.add(client);
						out.println("<h2>New client is added successfully</h2>");
						logger.info("Add new client " + ip + ":" + port);
					}
				} catch (NumberFormatException e) {
					logger.error("Invalid port value - " + port, e);
					out.println("<h2>Port must be integer value!!!</h2>");
				}
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
		this.dao = (DataDAO<Client>) ac.getBean("DataDAO");
	}

}
