package com.example.springboottesting.integration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractionContainerBaseTest {

	static final MySQLContainer MY_SQL_CONTAINER;

	static {
		MY_SQL_CONTAINER = new MySQLContainer("mysql:latest")
												.withUsername("root")
												.withPassword("root")
												.withDatabaseName("ems");

		MY_SQL_CONTAINER.start();

		System.out.println("Database name : "+MY_SQL_CONTAINER.getDatabaseName());
		System.out.println("JBDC Url : "+MY_SQL_CONTAINER.getJdbcUrl());
	}

	//To bind with application context.So that other classes can also link to mysql database
	@DynamicPropertySource
	public static void dynamicropertySource(DynamicPropertyRegistry registry){
		registry.add("spring.datasource.url", MY_SQL_CONTAINER :: getJdbcUrl);
		registry.add("spring.datasource.username", MY_SQL_CONTAINER :: getUsername);
		registry.add("spring.datasource.password", MY_SQL_CONTAINER :: getPassword);

	}
}
