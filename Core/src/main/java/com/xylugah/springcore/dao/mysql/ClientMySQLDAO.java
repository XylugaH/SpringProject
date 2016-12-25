package com.xylugah.springcore.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.xylugah.springcore.dao.DataDAO;
import com.xylugah.springcore.entity.Client;

public class ClientMySQLDAO implements DataDAO<Client, Integer> {

	private static final Logger logger = Logger.getLogger(ClientMySQLDAO.class);
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

	private String URL;
	private String user;
	private String password;

	private String insertQuery = "INSERT INTO clients(ip, port) VALUES(?, ?)";
	private String deleteQuery = "DELETE FROM clients WHERE id=?";
	private String getByIdQuery = "SELECT * FROM clients WHERE id=?";
	private String getAllQuery = "SELECT * FROM clients ORDER BY id";

	@Override
	public void add(final Client client) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, client.getIp());
			stmt.setInt(2, client.getPort());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error ", e);
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}

	}

	@Override
	public boolean removeById(final Integer key) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(deleteQuery);
			stmt.setInt(1, key);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("Error ", e);
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}
	}

	@Override
	public Client getById(final Integer key) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(getByIdQuery);
			stmt.setLong(1, key);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Client client = new Client();
				client.setId(rs.getInt("id"));
				client.setIp(rs.getString("ip"));
				client.setPort(rs.getInt("port"));

				return client;
			} else {
				return null;
			}
		} catch (SQLException e) {
			logger.error("Error ", e);
			return null;
		} finally {
			close(stmt);
			close(conn);
		}
	}

	@Override
	public List<Client> getAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Client> list = new ArrayList<>();

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(getAllQuery);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getInt("id"));
				client.setIp(rs.getString("ip"));
				client.setPort(rs.getInt("port"));

				list.add(client);
			}
		} catch (SQLException e) {
			logger.error("Error ", e);
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}

		return list;
	}

	private Connection getConnection() {
		try {
			Class.forName(DRIVER_NAME);
			return DriverManager.getConnection(this.URL, this.user, this.password);
		} catch (Exception e) {
			logger.error("Error ", e);
			throw new RuntimeException(e);
		}
	}

	private static void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				logger.error("Error ", e);
				throw new RuntimeException(e);
			}
		}
	}

	private static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				logger.error("Error ", e);
				throw new RuntimeException(e);
			}
		}
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
