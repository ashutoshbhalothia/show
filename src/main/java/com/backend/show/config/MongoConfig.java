//package com.backend.show.config;
//
//import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.ServerAddress;
//import org.jetbrains.annotations.NotNull;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
//
//@Configuration
//public class MongoConfig extends AbstractMongoClientConfiguration {
//
//    private static final Logger logger = LoggerFactory.getLogger(MongoConfig.class);
//
//    @Value("#{'${mongo.server:localhost}'.split(',')}")
//    private String server;
//
//    @Value("${mongo.port:27017}")
//    private Integer      port;
//
//    @Value("${mongo.username:}")
//    private String mongoUser;
//
//    @Value("${mongo.password:}")
//    private String password;
//
//    @Value("${mongo.db.name:}")
//    private String dbName;
//
//    ServerAddress serverAddress = new ServerAddress(server.trim(), port);
//
//    @Override
//    protected String getDatabaseName() {
//        return "test";
//    }
//
//    protected void configureClientSettings(MongoClientSettings.@NotNull Builder builder) {
//        String mongoUri = "mongodb://"+mongoUser+":"+password+"@"+serverAddress+"/"+dbName;
//        builder.applyConnectionString(new ConnectionString(mongoUri));
//    }
//}
