package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
public class ChartProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChartProjectApplication.class, args);
    }

    

}
