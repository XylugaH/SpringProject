package com.xylugah.springcore.dao.mongo;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.xylugah.springcore.dao.DataDAO;
import com.xylugah.springcore.entity.Client;

public class ClientMongoDBDAO implements DataDAO<Client> {

	private MongoClient mongo = null;
	private DB db = null;

	private final int dbPort = 27017;
	private final String dbHost = "localhost";
	private final String dbname = "ClientDB";
	private final String dbCollection = "client";

	public ClientMongoDBDAO() {
		getConnection();
	}

	@Override
	public void add(final Client client) {
		DBCollection table = db.getCollection(this.dbCollection);
		BasicDBObject document = new BasicDBObject();
		document.put("id", 2);
		document.put("ip", client.getIp());
		document.put("port", client.getPort());
		table.insert(document);
	}

	@Override
	public boolean removeById(final int id) {
		DBCollection table = db.getCollection(this.dbCollection);

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", id);
		DBObject cursor = table.findOne(searchQuery);

		if (cursor != null) {
			table.remove(searchQuery);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Client getById(int id) {
		DBCollection table = db.getCollection(this.dbCollection);

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", id);
		DBObject cursor = table.findOne(searchQuery);
		if (cursor != null) {
			Client client = new Client();
			client.setId((Integer) cursor.get("id"));
			client.setIp(String.valueOf(cursor.get("ip")));
			client.setPort((Integer) cursor.get("port"));
			return client;
		} else {
			return null;
		}

	}

	@Override
	public List<Client> getAll() {
		List<Client> clientList = new ArrayList<>();
		DBCollection table = db.getCollection(this.dbCollection);
		DBCursor cursor = table.find();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			Client client = new Client();
			client.setId((Integer) obj.get("id"));
			client.setIp(String.valueOf(obj.get("ip")));
			client.setPort((Integer) obj.get("port"));
			clientList.add(client);
		}
		if (clientList.isEmpty()) {
			return null;
		} else {
			return clientList;
		}
	}

	@SuppressWarnings("deprecation")
	private void getConnection() {
		this.mongo = new MongoClient(this.dbHost, this.dbPort);
		this.db = mongo.getDB(dbname);
	}

}
