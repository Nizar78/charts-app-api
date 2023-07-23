package com.example.demo.web;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Tutorial;
import com.example.demo.helper.CSVHelper;
import com.example.demo.message.ResponseMessage;
import com.example.demo.repository.TutorialRepository;
import com.example.demo.services.TutorialServices;

@CrossOrigin("http://localhost:4200") // Allowed domain to access this API
@Controller
@RequestMapping("/api/csv")

public class TutorialRestController {
    
	@Autowired
	TutorialRepository tutorialRepository;
	@Autowired
	TutorialServices tutorialServices;
	@GetMapping("/tutorials")
	  public ResponseEntity<Page<Tutorial>> getAllTutorials(@RequestParam(name="page",defaultValue = "0")int page, 
				@RequestParam(name="size", defaultValue = "3") int size) {
	    try {

	      Page<Tutorial> tutorials = tutorialServices.getAllTutorials(page,size);
	      if (tutorials.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(tutorials, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	@GetMapping("/tutorials/{id}")
	  public ResponseEntity<Tutorial> getTutorialById(@PathVariable Long id) {
	    try {
	      Tutorial tutorial = tutorialServices.getTutorialById(id);
	      return new ResponseEntity<>(tutorial, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 @PostMapping("/upload")
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    if (CSVHelper.hasCSVFormat(file)) {
	      try {
	    	  tutorialServices.save(file);
	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	      } catch (Exception e) {
	        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	      }
	    }
	    message = "Please upload a csv file!";
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	  }
	 @GetMapping("/delete")
	 public ResponseEntity<ResponseMessage> delete(Long id){
		 String message="";
		 try {
			 	tutorialRepository.deleteById(id);
		        message = "The item deleted successfuly";
		        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		      } catch (Exception e) {
		        message = "Can't delete the item";
		        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		      }
	 }
	 @PostMapping("/update/{id}")
	 public ResponseEntity<ResponseMessage> update(@RequestBody Tutorial tutorial){
		 String message="";
		 try {
			 	tutorialRepository.save(tutorial);
			 	System.out.print(tutorial.toString());
		        message = "The item updated successfuly";
		        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		      } catch (Exception e) {
		        message = "Can't updated the item";
		        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		      }
	 }
	 @PostMapping("/generate/random")
	    public ResponseEntity<ResponseMessage> generate() {
	        String message = "";

	        try {
	            Tutorial tutorial = new Tutorial();
	            tutorial.setId(1L); // Set ID to 1
	            // Generate random title, description, and published status
	            tutorial.setTitle(generateRandomString());
	            tutorial.setDescription(generateRandomString());
	            tutorial.setPublished(Math.random() < 0.5); // Randomly set published to true or false

	            tutorialRepository.save(tutorial);
	            System.out.print(tutorial.toString());

	            message = "The item generated successfuly";
	            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	        } catch (Exception e) {
	            message = "Can't generate the item";
	            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	        }
	    }

	    private String generateRandomString() {
	        return UUID.randomUUID().toString();
	    }
}
