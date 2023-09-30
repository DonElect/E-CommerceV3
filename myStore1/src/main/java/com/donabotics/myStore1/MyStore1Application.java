package com.donabotics.myStore1;

import com.donabotics.myStore1.dao.CustomerDAO;
import com.donabotics.myStore1.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyStore1Application {

	public static void main(String[] args) {
		SpringApplication.run(MyStore1Application.class, args);
	}

}
