package com.example.demo.services;

import java.io.IOException;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Tutorial;
import com.example.demo.helper.CSVHelper;
import com.example.demo.repository.TutorialRepository;
@Service

public class TutorialServices {
	
	 @Autowired
	 TutorialRepository repository;
	  public void save(MultipartFile file) {
	    try {
	      List<Tutorial> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
	      repository.saveAll(tutorials);
	    } catch (IOException e) {
	      throw new RuntimeException("fail to store csv data: " + e.getMessage());
	    }
	  }
	  public Page<Tutorial> getAllTutorials(int page, int size) {
	    Page<Tutorial> findAll =  repository.findAll(PageRequest.of(page,size));
		return findAll;
	  }
	  
	  public Tutorial getTutorialById(Long id) {
		    return repository.findById(id).get();
		  }

}
