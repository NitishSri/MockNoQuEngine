package com.mock.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mock.conf.HibernateJPAConf;
import com.mock.repositories.UserNewRepo;
import com.mock.repositories.UserRepo;
import com.mysql.entities.User;
import com.mysql.entities.UserNew;

public class CheckUser {
	
	private static Map<String,String> nameMap = new HashMap<String,String>();
	private static ApplicationContext ctxMongo = null;
	private static ApplicationContext ctxMySQL = null;
            
	
	static{
		nameMap.put("manu", "Manu Sharma");
		nameMap.put("nitish", "Nitish Srivastava");
		/*if (null== ctxMongo){
			ctxMongo = new AnnotationConfigApplicationContext(BaseConfiguration.class);
		}*/
		
		if (null== ctxMySQL){
			ctxMySQL = new AnnotationConfigApplicationContext(HibernateJPAConf.class);
		}
		
	}
	
	
	
	/*public static User getUser(String name){
		
		MongoOperations mongoOperation = (MongoOperations) ctxMongo.getBean("mongoTemplate");
		
		Query searchUserQuery = new Query(Criteria.where("name").is("Manu"));
		 
		// find the saved user again.
		User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
		
		String resultStr="";
		if (null==nameMap.get(name.toLowerCase())){
			resultStr = "Oops!! User Doesn't Exist";
		}else{
			resultStr = nameMap.get(name.toLowerCase());
		}
		
		return savedUser;
	}*/

	/*public static User validateForLogin(User user) {
		
		MongoOperations mongoOperation = (MongoOperations) ctxMongo.getBean("mongoTemplate");
		
		Query searchUserQuery = new Query(Criteria.where("name").is(user.getUsername()));
		searchUserQuery.addCriteria(Criteria.where("password").is(user.getPassword()));
		return  mongoOperation.findOne(searchUserQuery, User.class);
	}*/

	public static User getUser(String name){
		
		User user = new User();
		user.setUsername(name);
		
		return validateForLoginMySQL(user);
	}
	
	public static User validateForLoginMySQL(User user){
		UserRepo userRepo = (UserRepo)ctxMySQL.getBean("userRepo");
		List<User> usersFromDB = userRepo.findByUsername(user.getUsername());
		if(null!=usersFromDB && usersFromDB.size()>0){
			return usersFromDB.get(0);
		}
		//else
		return null;
		}
	
	
	public static UserNew validateForLoginMySQL(UserNew user){
		UserNewRepo userRepo = (UserNewRepo)ctxMySQL.getBean("userNewRepo");
		List<UserNew> usersFromDB = userRepo.findByUsername(user.getUsername());
		
		if(null!=usersFromDB && usersFromDB.size()>0){
			return usersFromDB.get(0);
		}
		//else
		return null;
		}
	
	public static User createUserMySQL(User user){
		UserRepo userRepo = (UserRepo)ctxMySQL.getBean("userRepo");
		User userFromDB = userRepo.save(user);
		
		if(null!=userFromDB){
			return userFromDB;
		}
		//else
		return null;
		}
	
	public static void main(String[] args){
		/*
		//My SQL Test
		User user = new User();
		user.setUsername("Manu");
		
		
		user.setAcType("user");
		user.setAddress("tajmahal");
		user.setPassword("pass");
		user.setFullname("Manu Sharma");
		
		User userFromDB = CheckUser.validateForLoginMySQL(user);

		//User userFromDB = CheckUser.createUserMySQL(user);
		
		System.out.println("User Name:" + userFromDB.getFullname() );
		*/
		
		UserNew user = new UserNew();
		user.setUsername("Manu");
		UserNew userFromDB = CheckUser.validateForLoginMySQL(user);
		
		
		System.out.println("User Name:" + userFromDB.getFullname() );
		
		
		
	}
	
	
}
