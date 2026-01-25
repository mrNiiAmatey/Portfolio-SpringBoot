package com.niiamatey.portfolio;

import com.niiamatey.portfolio.controller.PortfolioApiController;
import com.niiamatey.portfolio.controller.PortfolioController;
import com.niiamatey.portfolio.model.Experience;
import com.niiamatey.portfolio.model.Project;
import com.niiamatey.portfolio.model.Skill;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for the Portfolio application controllers.
 * 
 * Tests both the web MVC controller (Thymeleaf views) and 
 * the REST API controller (JSON responses).
 */
@WebMvcTest({PortfolioController.class, PortfolioApiController.class})
class PortfolioApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static class TestConfig {
        
        @Bean
        public List<Skill> skills() {
            return Arrays.asList(
                Skill.builder()
                    .category("Languages")
                    .icon("☕")
                    .technologies(Arrays.asList("Java", "Python"))
                    .build()
            );
        }

        @Bean
        public List<Project> projects() {
            return Arrays.asList(
                Project.builder()
                    .id("test-project")
                    .title("Test Project")
                    .type("Test")
                    .description("A test project")
                    .technologies(Arrays.asList("Java", "Spring Boot"))
                    .githubUrl("https://github.com/test/test")
                    .icon("🧪")
                    .build()
            );
        }

        @Bean
        public List<Experience> experiences() {
            return Arrays.asList(
                Experience.builder()
                    .dateRange("2023 - Present")
                    .title("Software Engineer")
                    .organization("Test Company")
                    .description("Test description")
                    .type(Experience.ExperienceType.WORK)
                    .build()
            );
        }
    }

    // ==========================================
    // Web MVC Controller Tests
    // ==========================================

    @Test
    @DisplayName("GET / should return homepage with model attributes")
    void homePageShouldReturnIndexView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("skills"))
                .andExpect(model().attributeExists("projects"))
                .andExpect(model().attributeExists("experiences"))
                .andExpect(model().attributeExists("contactForm"))
                .andExpect(model().attribute("name", "Nii Amatey Tagoe"));
    }

    @Test
    @DisplayName("POST /contact with valid data should redirect with success message")
    void contactFormSubmissionShouldRedirect() throws Exception {
        mockMvc.perform(post("/contact")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "John Doe")
                        .param("email", "john@example.com")
                        .param("subject", "Job Opportunity")
                        .param("message", "I have a job opportunity for you!"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/#contact"))
                .andExpect(flash().attributeExists("successMessage"));
    }

    @Test
    @DisplayName("POST /contact with invalid data should return form with errors")
    void contactFormWithInvalidDataShouldShowErrors() throws Exception {
        mockMvc.perform(post("/contact")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "")  // Empty name
                        .param("email", "invalid-email")  // Invalid email
                        .param("subject", "Hi")  // Too short
                        .param("message", "Short"))  // Too short
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().hasErrors());
    }

    // ==========================================
    // REST API Controller Tests
    // ==========================================

    @Test
    @DisplayName("GET /api/v1/portfolio should return complete portfolio data")
    void apiPortfolioShouldReturnAllData() throws Exception {
        mockMvc.perform(get("/api/v1/portfolio")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Nii Amatey Tagoe")))
                .andExpect(jsonPath("$.skills", hasSize(1)))
                .andExpect(jsonPath("$.projects", hasSize(1)))
                .andExpect(jsonPath("$.experiences", hasSize(1)));
    }

    @Test
    @DisplayName("GET /api/v1/skills should return skills list")
    void apiSkillsShouldReturnSkillsList() throws Exception {
        mockMvc.perform(get("/api/v1/skills")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].category", is("Languages")));
    }

    @Test
    @DisplayName("GET /api/v1/projects should return projects list")
    void apiProjectsShouldReturnProjectsList() throws Exception {
        mockMvc.perform(get("/api/v1/projects")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Test Project")));
    }

    @Test
    @DisplayName("GET /api/v1/projects/{id} should return specific project")
    void apiProjectByIdShouldReturnProject() throws Exception {
        mockMvc.perform(get("/api/v1/projects/test-project")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is("test-project")))
                .andExpect(jsonPath("$.title", is("Test Project")));
    }

    @Test
    @DisplayName("GET /api/v1/projects/{id} with invalid ID should return 404")
    void apiProjectByInvalidIdShouldReturn404() throws Exception {
        mockMvc.perform(get("/api/v1/projects/non-existent")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /api/v1/experiences should return experiences list")
    void apiExperiencesShouldReturnExperiencesList() throws Exception {
        mockMvc.perform(get("/api/v1/experiences")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Software Engineer")));
    }

    @Test
    @DisplayName("GET /api/v1/health should return UP status")
    void apiHealthShouldReturnUpStatus() throws Exception {
        mockMvc.perform(get("/api/v1/health")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("UP")))
                .andExpect(jsonPath("$.service", is("portfolio-api")));
    }
}
