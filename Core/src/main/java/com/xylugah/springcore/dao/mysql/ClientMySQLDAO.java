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

public class ClientMySQLDAO implements DataDAO<Client> {

	private static final Logger logger = Logger.getLogger(ClientMySQLDAO.class);
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private String URL;
	private String user;
	private String password;

	@Override
	public void add(final Client client) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("INSERT INTO client(ip, port) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, client.getIp());
			stmt.setInt(2, client.getPort());

			int result = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}

	}

	@Override
	public boolean removeById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("DELETE FROM user WHERE id=?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(stmt);
			close(conn);
		}
	}

	@Override
	public Client getById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM client WHERE id=?");
			stmt.setLong(1, id);

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
			stmt = conn.prepareStatement("SELECT * FROM client ORDER BY id");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getInt("id"));
				client.setIp(rs.getString("ip"));
				client.setPort(rs.getInt("port"));

				list.add(client);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
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
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private static void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	private static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

}
