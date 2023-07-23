package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Tutorial;
import com.example.demo.entities.User;
import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

public interface UserRepository extends  JpaRepository<User, Long>  {
	
	
    User findByUsername(String username);

}
