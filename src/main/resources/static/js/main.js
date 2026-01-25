/**
 * Portfolio Website JavaScript
 * Handles animations, smooth scrolling, and interactions
 * 
 * @author Nii Amatey Tagoe
 */

document.addEventListener('DOMContentLoaded', function() {
    initScrollAnimations();
    initSmoothScroll();
    initNavbarScroll();
    initFormValidation();
});

/**
 * Initialize scroll-triggered fade-in animations
 * Uses Intersection Observer for performance
 */
function initScrollAnimations() {
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };

    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('visible');
            }
        });
    }, observerOptions);

    document.querySelectorAll('.fade-in').forEach(el => {
        observer.observe(el);
    });
}

/**
 * Initialize smooth scrolling for anchor links
 */
function initSmoothScroll() {
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function(e) {
            e.preventDefault();
            const target = document.querySelector(this.getAttribute('href'));
            if (target) {
                target.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            }
        });
    });
}

/**
 * Initialize navbar background change on scroll
 */
function initNavbarScroll() {
    const nav = document.querySelector('nav');
    
    window.addEventListener('scroll', () => {
        if (window.scrollY > 50) {
            nav.style.background = 'rgba(10, 10, 11, 0.95)';
        } else {
            nav.style.background = 'rgba(10, 10, 11, 0.8)';
        }
    });
}

/**
 * Initialize client-side form validation
 */
function initFormValidation() {
    const form = document.querySelector('.contact-form');
    
    if (form) {
        form.addEventListener('submit', function(e) {
            const inputs = form.querySelectorAll('input, textarea');
            let isValid = true;
            
            inputs.forEach(input => {
                if (input.hasAttribute('required') && !input.value.trim()) {
                    isValid = false;
                    input.classList.add('error');
                } else {
                    input.classList.remove('error');
                }
            });
            
            // Email validation
            const emailInput = form.querySelector('input[type="email"]');
            if (emailInput && emailInput.value) {
                const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (!emailRegex.test(emailInput.value)) {
                    isValid = false;
                    emailInput.classList.add('error');
                }
            }
            
            if (!isValid) {
                e.preventDefault();
            }
        });
        
        // Remove error class on input
        form.querySelectorAll('input, textarea').forEach(input => {
            input.addEventListener('input', function() {
                this.classList.remove('error');
            });
        });
    }
}

/**
 * Utility: Debounce function for performance
 */
function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
            clearTimeout(timeout);
            func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}

/**
 * Log portfolio view for analytics (placeholder)
 */
function logPageView() {
    console.log('Portfolio page viewed at:', new Date().toISOString());
    // In production, integrate with Google Analytics or similar
}

// Log page view
logPageView();
