package com.xylugah.springcore.dao.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public abstract class MongoDBConnector {
	private MongoClient mongo = null;

	private final int dbPort = 27017;
	private final String dbHost = "localhost";
	private final String dbname = "ClientDB";

	@SuppressWarnings("deprecation")
	protected DB getConnection() {
		this.mongo = new MongoClient(this.dbHost, this.dbPort);
		return mongo.getDB(dbname);
	}
}
