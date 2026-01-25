package com.niiamatey.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an experience or education entry in the timeline.
 * 
 * This model is used to display work experience, education,
 * and other relevant background information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Experience {
    
    /**
     * Date range or period (e.g., "2023 - Expected 2025", "Current")
     */
    private String dateRange;
    
    /**
     * Title of the position or degree
     */
    private String title;
    
    /**
     * Company or institution name
     */
    private String organization;
    
    /**
     * Description of responsibilities or achievements
     */
    private String description;
    
    /**
     * Type of entry: WORK, EDUCATION, or OTHER
     */
    private ExperienceType type;
    
    /**
     * Enum representing the type of experience entry
     */
    public enum ExperienceType {
        WORK,
        EDUCATION,
        OTHER
    }
}
