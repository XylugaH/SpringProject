package com.xylugah.springcore.dao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.xylugah.springcore.model.Client;

public class DataMongoDBDAO implements DataDAO {
	private MongoClient mongo = null;
	private DB db = null;

	public DataMongoDBDAO() {
		getConnection();
	}

	@Override
	public void add(final String ip, final int port) {
		DBCollection table = db.getCollection("client");
		BasicDBObject document = new BasicDBObject();
		document.put("id", 2);
		document.put("ip", ip);
		document.put("port", port);
		table.insert(document);
	}

	@Override
	public boolean remove(final int id) {
		DBCollection table = db.getCollection("client");

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
		DBCollection table = db.getCollection("client");

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
	public List<Client> getClientList() {
		List<Client> clientList = new ArrayList<>();
		DBCollection table = db.getCollection("client");
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

	private void getConnection() {
		this.mongo = new MongoClient("localhost", 27017);
		this.db = mongo.getDB("ClientDB");
	}

}
