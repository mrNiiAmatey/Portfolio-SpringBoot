package com.niiamatey.portfolio.controller;

import com.niiamatey.portfolio.model.ContactForm;
import com.niiamatey.portfolio.model.Experience;
import com.niiamatey.portfolio.model.Project;
import com.niiamatey.portfolio.model.Skill;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Main controller for the portfolio website.
 * 
 * Handles all page requests and populates the model with
 * portfolio data for Thymeleaf template rendering.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class PortfolioController {

    private final List<Skill> skills;
    private final List<Project> projects;
    private final List<Experience> experiences;

    /**
     * Displays the main portfolio homepage.
     * 
     * @param model Spring MVC model for passing data to the view
     * @return the index template name
     */
    @GetMapping("/")
    public String home(Model model) {
        log.info("Loading portfolio homepage");
        
        model.addAttribute("skills", skills);
        model.addAttribute("projects", projects);
        model.addAttribute("experiences", experiences);
        model.addAttribute("contactForm", new ContactForm());
        
        // Personal information
        model.addAttribute("name", "Nii Amatey Tagoe");
        model.addAttribute("title", "Software Engineer");
        model.addAttribute("email", "your-email@example.com");
        model.addAttribute("linkedin", "https://linkedin.com/in/your-profile");
        model.addAttribute("github", "https://github.com/mrNiiAmatey");
        model.addAttribute("location", "Dallas-Fort Worth, TX");
        
        return "index";
    }

    /**
     * Handles contact form submissions.
     * 
     * @param contactForm the submitted contact form data
     * @param bindingResult validation results
     * @param redirectAttributes attributes for flash messages
     * @return redirect to homepage with success/error message
     */
    @PostMapping("/contact")
    public String submitContact(
            @Valid @ModelAttribute("contactForm") ContactForm contactForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {
        
        if (bindingResult.hasErrors()) {
            log.warn("Contact form validation failed: {}", bindingResult.getAllErrors());
            
            // Re-populate the model for re-rendering the page
            model.addAttribute("skills", skills);
            model.addAttribute("projects", projects);
            model.addAttribute("experiences", experiences);
            model.addAttribute("name", "Nii Amatey Tagoe");
            model.addAttribute("title", "Software Engineer");
            model.addAttribute("email", "your-email@example.com");
            model.addAttribute("linkedin", "https://linkedin.com/in/your-profile");
            model.addAttribute("github", "https://github.com/mrNiiAmatey");
            model.addAttribute("location", "Dallas-Fort Worth, TX");
            
            return "index";
        }
        
        // Log the contact submission (in production, send email or save to database)
        log.info("Contact form submitted - Name: {}, Email: {}, Subject: {}", 
                contactForm.getName(), contactForm.getEmail(), contactForm.getSubject());
        
        // In a real application, you would:
        // 1. Send an email notification
        // 2. Save to a database
        // 3. Integrate with a CRM
        
        redirectAttributes.addFlashAttribute("successMessage", 
                "Thank you for your message! I'll get back to you soon.");
        
        return "redirect:/#contact";
    }

    /**
     * Displays the projects page (alternative dedicated page).
     * 
     * @param model Spring MVC model
     * @return the projects template name
     */
    @GetMapping("/projects")
    public String projectsPage(Model model) {
        log.info("Loading projects page");
        model.addAttribute("projects", projects);
        return "projects";
    }

    /**
     * Displays the resume/about page.
     * 
     * @param model Spring MVC model
     * @return the about template name
     */
    @GetMapping("/about")
    public String aboutPage(Model model) {
        log.info("Loading about page");
        model.addAttribute("skills", skills);
        model.addAttribute("experiences", experiences);
        return "about";
    }
}
