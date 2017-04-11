/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.util;

import com.github.jmkgreen.morphia.AdvancedDatastore;
import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.Morphia;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.ServerAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class MongoDBConnection {
	protected static Logger logger = LoggerFactory
			.getLogger(MongoDBConnection.class);
	private static Mongo instance = null;

	public static Datastore getDataStore(String dbname) {
		Morphia morphia = new Morphia();
		AdvancedDatastore ads = (AdvancedDatastore) morphia.createDatastore(
				getMongo(), dbname);
		return ads;
	}

	public static DB getDB(String dbname) {
		return getMongo().getDB(dbname);
	}

	public static Mongo getInstance() {
		return instance;
	}

	public static synchronized Mongo getMongo() {
		if (instance == null)
			try {
				List saList = new ArrayList();
				saList.add(new ServerAddress(LKLConfig.getValue("mongo.ip"),
						Integer.parseInt(LKLConfig.getValue("mongo.port"))));

				MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
				MongoClientOptions options = builder.build();

				builder.description("Lakala Mongo ！");

				builder.autoConnectRetry(true);

				builder.connectionsPerHost(Integer.valueOf(
						LKLConfig.getValue("mongo.connectionsPerHost"))
						.intValue());

				builder.connectTimeout(30000);

				builder.maxWaitTime(120000);

				builder.socketKeepAlive(true);

				builder.socketTimeout(0);

				builder.maxAutoConnectRetryTime(3629520026823819265L);

				builder.threadsAllowedToBlockForConnectionMultiplier(50);

				instance = new MongoClient(saList);
			} catch (UnknownHostException e) {
				logger.error("Mongon数据库服务器连接失败！", e);
				return null;
			}

		return instance;
	}

	public static void setInstance(Mongo instance) {
		instance = instance;
	}
}
