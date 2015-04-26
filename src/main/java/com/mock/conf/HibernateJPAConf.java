package com.mock.conf;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.mysql.entities")
@EnableJpaRepositories(basePackages = {"com.mock.repositories"})
@EnableTransactionManagement
public class HibernateJPAConf {

	@Bean
	public DataSource mySQLDataSource(){
		
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		//ds.setUrl("jdbc:mysql://127.6.38.132:3306/mockqu");
		ds.setUrl("jdbc:mysql://127.0.0.1:3306/mockqu");
		ds.setUsername("manu");
		ds.setPassword("Welcome12#");
		return ds;
		
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
			LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
			emf.setDataSource(mySQLDataSource());
			emf.setPackagesToScan("com.mysql.entities");
			emf.setPersistenceUnitName("mockquPU");
			return emf;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory().getObject()) ;
	}
	
}
