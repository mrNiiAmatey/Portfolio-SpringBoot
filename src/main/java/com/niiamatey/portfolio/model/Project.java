package com.niiamatey.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a portfolio project with all relevant details.
 * 
 * This model is used to display project information on the portfolio website,
 * including title, description, technologies used, and links.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    
    /**
     * Unique identifier for the project
     */
    private String id;
    
    /**
     * Project title displayed on the portfolio
     */
    private String title;
    
    /**
     * Category/type of project (e.g., "Microservice", "Full-Stack", "Machine Learning")
     */
    private String type;
    
    /**
     * Brief description of the project and its purpose
     */
    private String description;
    
    /**
     * List of technologies used in the project
     */
    private List<String> technologies;
    
    /**
     * URL to the GitHub repository
     */
    private String githubUrl;
    
    /**
     * URL to live demo (optional)
     */
    private String demoUrl;
    
    /**
     * Emoji or icon representing the project
     */
    private String icon;
    
    /**
     * Key highlights or features of the project
     */
    private List<String> highlights;
}
