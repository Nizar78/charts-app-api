package com.example.demo.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString    // This annotation of lombok to generate getters , setters , etc.
@Entity

public class User {
	  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  private String username;
	  private String password;
	  private String role;
	
}
