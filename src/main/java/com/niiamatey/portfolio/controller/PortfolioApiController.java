package com.niiamatey.portfolio.controller;

import com.niiamatey.portfolio.model.Experience;
import com.niiamatey.portfolio.model.Project;
import com.niiamatey.portfolio.model.Skill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST API controller providing programmatic access to portfolio data.
 * 
 * This API can be used by:
 * - Frontend JavaScript for dynamic content loading
 * - Third-party applications that want to display your portfolio
 * - Mobile applications
 * - Integration with other services
 * 
 * All endpoints return JSON responses.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PortfolioApiController {

    private final List<Skill> skills;
    private final List<Project> projects;
    private final List<Experience> experiences;

    /**
     * Returns all portfolio data in a single response.
     * 
     * GET /api/v1/portfolio
     * 
     * @return complete portfolio data including skills, projects, and experiences
     */
    @GetMapping("/portfolio")
    public ResponseEntity<Map<String, Object>> getPortfolio() {
        log.info("API request: GET /api/v1/portfolio");
        
        Map<String, Object> portfolio = new HashMap<>();
        portfolio.put("name", "Nii Amatey Tagoe");
        portfolio.put("title", "Software Engineer");
        portfolio.put("email", "armahtey@gmail.com");
        portfolio.put("github", "https://github.com/mrNiiAmatey");
        portfolio.put("linkedin", "https://www.linkedin.com/in/mrarmahtey/");
        portfolio.put("location", "Dallas-Fort Worth, TX");
        portfolio.put("skills", skills);
        portfolio.put("projects", projects);
        portfolio.put("experiences", experiences);
        
        return ResponseEntity.ok(portfolio);
    }

    /**
     * Returns all skills.
     * 
     * GET /api/v1/skills
     * 
     * @return list of skill categories with technologies
     */
    @GetMapping("/skills")
    public ResponseEntity<List<Skill>> getSkills() {
        log.info("API request: GET /api/v1/skills");
        return ResponseEntity.ok(skills);
    }

    /**
     * Returns all projects.
     * 
     * GET /api/v1/projects
     * 
     * @return list of portfolio projects
     */
    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getProjects() {
        log.info("API request: GET /api/v1/projects");
        return ResponseEntity.ok(projects);
    }

    /**
     * Returns a specific project by ID.
     * 
     * GET /api/v1/projects/{id}
     * 
     * @param id the project identifier
     * @return the project if found, 404 otherwise
     */
    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable String id) {
        log.info("API request: GET /api/v1/projects/{}", id);
        
        Optional<Project> project = projects.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        
        return project.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Returns all experiences.
     * 
     * GET /api/v1/experiences
     * 
     * @return list of work and education experiences
     */
    @GetMapping("/experiences")
    public ResponseEntity<List<Experience>> getExperiences() {
        log.info("API request: GET /api/v1/experiences");
        return ResponseEntity.ok(experiences);
    }

    /**
     * Health check endpoint.
     * 
     * GET /api/v1/health
     * 
     * @return status indicating the API is operational
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("service", "portfolio-api");
        status.put("version", "1.0.0");
        return ResponseEntity.ok(status);
    }
}
