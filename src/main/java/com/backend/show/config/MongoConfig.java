package com.backend.show.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.backend")
public class MongoConfig extends AbstractMongoClientConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(MongoConfig.class);

    @Value("#{'${mongo.server:}'.split(',')}")
    private String server;

    @Value("${mongo.port:}")
    private Integer      port;

//    @Value("${mongo.username:}")
//    private String mongoUser;
//
//    @Value("${mongo.password:}")
//    private String password;

    @Value("${mongo.db.name:}")
    private String dbName;

    private int heartBeatSocketTimeOut  = 10000;

    private int socketTimeout           = 10000;

    private int connectTimeout          = 10000;

    private int heartbeatFrequency      = 10000;

    private int heartBeatConnectTimeout = 10000;

    private int maxConnectionIdleTime   =  10 * 60 * 1000;

//    ServerAddress serverAddress = new ServerAddress(server.trim(), port);

    @Override
    protected String getDatabaseName() {
        return "test";
    }

//    protected void configureClientSettings(MongoClientSettings.@NotNull Builder builder) {
//        String mongoUri = "mongodb://"+serverAddress+"/"+dbName;
//        builder.applyConnectionString(new ConnectionString(mongoUri));
//    }

    @Override
    public MongoClient mongoClient() {
        final var connectionString = new ConnectionString("mongodb://"+server+":"+port+"/"+dbName);
        System.out.println(connectionString);
        final var mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        System.out.println(mongoClientSettings);
        return MongoClients.create(mongoClientSettings);
    }

}
