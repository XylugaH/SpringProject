package com.xylugah.springcore.dao.mongo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.xylugah.springcore.dao.DataDAO;
import com.xylugah.springcore.entity.Client;

public class ClientMongoDBDAO extends MongoDBConnector implements DataDAO<Client, Integer> {

	private static final Logger logger = Logger.getLogger(ClientMongoDBDAO.class);
	private DB db = null;
	private final String dbCollection;

	public ClientMongoDBDAO() {
		dbCollection = "client";
		this.db = getConnection();
	}

	@Override
	public void add(final Client entity) {
		DBCollection table = db.getCollection(this.dbCollection);
		BasicDBObject document = new BasicDBObject();
		document.put("id", 2);
		document.put("ip", entity.getIp());
		document.put("port", entity.getPort());
		table.insert(document);
		if (logger.isInfoEnabled()) {
			logger.info("Add client sucessfull!" + entity);
		}
	}

	@Override
	public boolean removeById(final Integer key) {
		DBCollection table = db.getCollection(this.dbCollection);

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", key);
		DBObject cursor = table.findOne(searchQuery);

		if (cursor != null) {
			table.remove(searchQuery);
			if (logger.isInfoEnabled()) {
				logger.info("Remove client by key" + key + "sucessfull!");
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Client getById(final Integer key) {
		DBCollection table = db.getCollection(this.dbCollection);

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", key);
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

}
