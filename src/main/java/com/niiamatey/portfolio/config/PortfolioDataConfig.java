package com.niiamatey.portfolio.config;

import com.niiamatey.portfolio.model.Experience;
import com.niiamatey.portfolio.model.Project;
import com.niiamatey.portfolio.model.Skill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Configuration class that provides portfolio data as Spring beans.
 * 
 * This centralizes all portfolio content, making it easy to update
 * and maintain. In a production environment, this data could come
 * from a database or external configuration.
 */
@Configuration
public class PortfolioDataConfig {

    /**
     * Provides the list of skills to display on the portfolio.
     */
    @Bean
    public List<Skill> skills() {
        return Arrays.asList(
            Skill.builder()
                .category("Languages")
                .icon("☕")
                .technologies(Arrays.asList("Java", "Python", "JavaScript", "TypeScript", "SQL"))
                .build(),
            
            Skill.builder()
                .category("Backend & Frameworks")
                .icon("⚙️")
                .technologies(Arrays.asList("Spring Boot", "Node.js", "Express", "REST APIs", "JUnit"))
                .build(),
            
            Skill.builder()
                .category("Cloud & DevOps")
                .icon("☁️")
                .technologies(Arrays.asList("AWS Lambda", "API Gateway", "DynamoDB", "S3", "Docker", "GitHub Actions"))
                .build(),
            
            Skill.builder()
                .category("Databases & Data")
                .icon("🗄️")
                .technologies(Arrays.asList("MongoDB", "PostgreSQL", "MySQL", "DynamoDB", "Redis"))
                .build()
        );
    }

    /**
     * Provides the list of projects to display on the portfolio.
     */
    @Bean
    public List<Project> projects() {
        return Arrays.asList(
            Project.builder()
                .id("qr-code-generator")
                .title("QR Code Generator API")
                .type("Microservice")
                .description("Production-ready Spring Boot REST API for generating customizable QR codes. " +
                           "Features comprehensive input validation, proper HTTP status codes, " +
                           "configurable parameters, and full test coverage.")
                .technologies(Arrays.asList("Java 17", "Spring Boot", "ZXing", "JUnit", "Maven"))
                .githubUrl("https://github.com/mrNiiAmatey/QRCode-Generator")
                .icon("🔲")
                .highlights(Arrays.asList(
                    "RESTful API design with proper status codes",
                    "Comprehensive input validation",
                    "Full test coverage with JUnit",
                    "Professional JavaDoc documentation"
                ))
                .build(),
            
            Project.builder()
                .id("aws-qa-platform")
                .title("AWS Q&A Platform")
                .type("Full-Stack Serverless")
                .description("Secure serverless application with Angular frontend deployed on AWS infrastructure. " +
                           "Features IAM-secured CRUD APIs, CORS configuration, and S3/CloudFront static hosting.")
                .technologies(Arrays.asList("AWS Lambda", "API Gateway", "DynamoDB", "Angular", "IAM"))
                .githubUrl("https://github.com/mrNiiAmatey/FullStackII_SNHU_AWS_Conpect")
                .icon("☁️")
                .highlights(Arrays.asList(
                    "Serverless architecture on AWS",
                    "IAM security implementation",
                    "S3/CloudFront deployment",
                    "Full CRUD operations"
                ))
                .build(),
            
            Project.builder()
                .id("mean-travel")
                .title("MEAN Travel Platform")
                .type("Full-Stack Web App")
                .description("Full-stack web application demonstrating MVC architecture with secure admin interface, " +
                           "RESTful API integration, and component-based frontend architecture.")
                .technologies(Arrays.asList("MongoDB", "Express.js", "Angular", "Node.js"))
                .githubUrl("https://github.com/mrNiiAmatey/cs465_FULLSTACK")
                .icon("🌐")
                .highlights(Arrays.asList(
                    "MVC architecture pattern",
                    "Secure admin interface",
                    "RESTful API design",
                    "Component-based frontend"
                ))
                .build(),
            
            Project.builder()
                .id("deep-q-learning")
                .title("Deep Q-Learning Agent")
                .type("Machine Learning")
                .description("Reinforcement learning agent using Deep Q-Networks to solve pathfinding challenges. " +
                           "Implements experience replay and epsilon-greedy exploration strategies.")
                .technologies(Arrays.asList("Python", "Keras", "TensorFlow", "NumPy"))
                .githubUrl("https://github.com/mrNiiAmatey/CS370Deep_Q-Learning")
                .icon("🤖")
                .highlights(Arrays.asList(
                    "Deep Q-Network implementation",
                    "Experience replay buffer",
                    "Epsilon-greedy exploration",
                    "Neural network architecture"
                ))
                .build()
        );
    }

    /**
     * Provides the list of experiences to display on the portfolio timeline.
     */
    @Bean
    public List<Experience> experiences() {
        return Arrays.asList(
            Experience.builder()
                .dateRange("2023 — Expected 2025")
                .title("B.S. Computer Science")
                .organization("Southern New Hampshire University")
                .description("Coursework in software engineering, data structures, algorithms, " +
                           "cloud computing, and machine learning. Building production-grade " +
                           "applications as part of capstone projects.")
                .type(Experience.ExperienceType.EDUCATION)
                .build(),
            
            Experience.builder()
                .dateRange("Current")
                .title("Certified Pharmacy Technician")
                .organization("Walmart")
                .description("Developed strong attention to detail, accuracy under pressure, " +
                           "and customer service skills while pursuing software engineering career transition.")
                .type(Experience.ExperienceType.WORK)
                .build(),
            
            Experience.builder()
                .dateRange("Ongoing")
                .title("Continuous Learning")
                .organization("Self-Directed")
                .description("Active competitive programming practice on HackerRank and LeetCode. " +
                           "Deepening expertise in Java multithreading, system design, and enterprise architecture patterns.")
                .type(Experience.ExperienceType.OTHER)
                .build()
        );
    }
}
