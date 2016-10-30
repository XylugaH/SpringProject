package com.xylugah.springserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xylugah.springcore.core.Client;
import com.xylugah.springcore.core.GetEnvironmentsRequest;
import com.xylugah.springcore.core.Request;
import com.xylugah.springcore.dao.DataMemoryDAO;
import com.xylugah.springcore.dao.IDataDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	IDataDAO dao = new DataMemoryDAO();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home11! The client locale is {}.", locale);
		String message = "Welcome home";

		model.addAttribute("message", message);

		return "home";
	}

	@RequestMapping(value = "/environments", method = RequestMethod.GET)
	public String environments(@RequestParam(value = "id", defaultValue = "-1") int id, Locale locale, Model model)
			throws IOException {

		String message;
		if (id < 0) {
			message = "List of environments...";
			Request request = new GetEnvironmentsRequest();
			int serverPort = 1234;
			String address = "127.0.0.1";
			try {
				InetAddress ipAddress = InetAddress.getByName(address);
				Socket socket = new Socket(ipAddress, serverPort);
				socket.close();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			Client client = dao.getById(id);
			message = "Environment by id " + client.getIp();
		}

		model.addAttribute("message", message);

		return "home";
	}

	@RequestMapping(value = "/environments/add", method = RequestMethod.GET)
	public String addEnvironment(@RequestParam(value = "ip", required = true, defaultValue = "-1") String ip,
			@RequestParam(value = "port", required = true, defaultValue = "-1") int port, Locale locale, Model model) {

		String message;
		message = "Add " + ip + port;

		dao.add(ip, port);

		model.addAttribute("message", message);

		return "home";
	}

	@RequestMapping(value = "/environments/remove", method = RequestMethod.GET)
	public String removeEnvironment(@RequestParam(value = "id", defaultValue = "-1") int id, Locale locale,
			Model model) {

		String message;
		if (id < 0) {
			message = "Remove environments...";
		} else {
			message = "Remove environment by id " + id;
		}

		model.addAttribute("message", message);

		return "home";
	}

	@RequestMapping(value = "/environments/total-environments-count", method = RequestMethod.GET)
	public String removeEnvironment(Locale locale, Model model) {

		String message = "total-environments-count";

		model.addAttribute("message", message);

		return "home";
	}

}
