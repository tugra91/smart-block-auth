package com.turkcell.blockmailAuth.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
public class OAuth2DatabaseConfiguration extends AbstractMongoConfiguration {
	
	@Override
	public MongoClient mongoClient() {
		
		MongoClientOptions options = new MongoClientOptions.Builder()
				.applicationName("blockMail")
				.build();
		MongoClient mongoClient = new MongoClient(Collections.singletonList(new ServerAddress("127.0.0.1", 27017)), 
									MongoCredential.createScramSha1Credential("admin", "admin", "141991".toCharArray()), options);
 
		return mongoClient;
	}

	@Override
	protected String getDatabaseName() {
		return "productDB";
	}
	
	@Bean
	public MongoDbFactory mongoDbFactory(){
		return new SimpleMongoDbFactory(mongoClient(), getDatabaseName() );
	}
	
	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoDbFactory());
	}

}
