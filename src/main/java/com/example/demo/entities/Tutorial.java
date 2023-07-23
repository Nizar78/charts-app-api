package com.example.demo.entities;

import javax.annotation.Generated;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data @AllArgsConstructor @NoArgsConstructor @ToString    // This annotation of lombok to generate getters , setters , etc.
@Entity
public class Tutorial {
	  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long id;
	  private String title;
	  private String description;
	  private boolean published;
	
}
