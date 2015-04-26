package com.mock.conf;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mongo.model.User;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
public class BaseConfiguration {

	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(new MongoClientURI("mongodb://admin:vYhVc2NK6msU@127.6.38.130")), "mockqu");
	}
 
	public @Bean
	MongoTemplate mongoTemplate() throws Exception {

		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
 
	}
	
	public static void main(String[] args){
		ApplicationContext ctx = 
	             new AnnotationConfigApplicationContext(BaseConfiguration.class);
		
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		
		Query searchUserQuery = new Query(Criteria.where("name").is("Manu"));
		 
		// find the saved user again.
		User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
		
		List<User> listUser = mongoOperation.findAll(User.class);
		System.out.println("Number of user = " + listUser.size());
	}
 
}
