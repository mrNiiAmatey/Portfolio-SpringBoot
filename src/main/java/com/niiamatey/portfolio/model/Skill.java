package com.niiamatey.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a skill category with associated technologies.
 * 
 * Skills are grouped by category (e.g., Languages, Backend, Cloud)
 * for organized display on the portfolio.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    
    /**
     * Category name (e.g., "Languages", "Backend & Frameworks")
     */
    private String category;
    
    /**
     * Icon/emoji representing the skill category
     */
    private String icon;
    
    /**
     * List of specific technologies in this category
     */
    private List<String> technologies;
}
