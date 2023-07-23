package com.example.demo.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Tutorial;
import com.example.demo.entities.User;
import com.example.demo.helper.CSVHelper;
import com.example.demo.message.ResponseMessage;
import com.example.demo.repository.TutorialRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.TutorialServices;

@CrossOrigin("http://localhost:4200") // Allowed domain to access this API
@Controller
@RequestMapping("/api/csv")

public class UserRestController {
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/index")
	public ResponseEntity<ResponseMessage> index(){
	      return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<ResponseMessage> addUser( @RequestBody User user) {
		String message="";
		 try {
			 	userRepository.save(user);
		        message = "The item added successfuly";
		        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		      } catch (Exception e) {
		        message = "Can't updated the item";
		        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		      }
	
	}
	

	
	
}
