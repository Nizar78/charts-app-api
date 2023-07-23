package com.example.demo.message;

import java.util.List;

import com.example.demo.entities.Tutorial;

public class TutorialPaginationResponse {
	
	private List<Tutorial> tutorials;
    private int totalPages;

    public TutorialPaginationResponse(List<Tutorial> tutorials, int totalPages) {
        this.tutorials = tutorials;
        this.totalPages = totalPages;
    }

}
