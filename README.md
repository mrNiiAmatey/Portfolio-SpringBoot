# Portfolio Website - Spring Boot Application

A professional portfolio website built with **Spring Boot**, **Thymeleaf**, and modern web technologies. This project demonstrates enterprise Java development practices including MVC architecture, RESTful APIs, form validation, and comprehensive testing.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.1-brightgreen)
![License](https://img.shields.io/badge/License-MIT-blue)

## 🚀 Features

- **MVC Architecture** - Clean separation of concerns with Spring MVC
- **Thymeleaf Templates** - Server-side rendering with dynamic content
- **REST API** - JSON endpoints for programmatic access
- **Form Validation** - Server-side validation with Bean Validation
- **Responsive Design** - Mobile-first CSS with modern animations
- **Health Endpoints** - Spring Actuator for monitoring
- **Docker Support** - Containerized deployment ready
- **CI/CD Pipeline** - GitHub Actions workflow included

## 📁 Project Structure

```
portfolio-springboot/
├── src/
│   ├── main/
│   │   ├── java/com/niiamatey/portfolio/
│   │   │   ├── PortfolioApplication.java      # Main entry point
│   │   │   ├── controller/
│   │   │   │   ├── PortfolioController.java   # Web MVC controller
│   │   │   │   └── PortfolioApiController.java # REST API controller
│   │   │   ├── model/
│   │   │   │   ├── Project.java               # Project data model
│   │   │   │   ├── Skill.java                 # Skill data model
│   │   │   │   ├── Experience.java            # Experience data model
│   │   │   │   └── ContactForm.java           # Contact form model
│   │   │   └── config/
│   │   │       └── PortfolioDataConfig.java   # Data configuration
│   │   └── resources/
│   │       ├── templates/
│   │       │   └── index.html                 # Main Thymeleaf template
│   │       ├── static/
│   │       │   ├── css/styles.css             # Stylesheet
│   │       │   └── js/main.js                 # JavaScript
│   │       └── application.properties          # Configuration
│   └── test/
│       └── java/com/niiamatey/portfolio/
│           └── PortfolioApplicationTests.java  # Unit tests
├── .github/workflows/ci.yml                    # CI/CD pipeline
├── Dockerfile                                   # Docker configuration
├── pom.xml                                      # Maven configuration
└── README.md                                    # This file
```

## 🛠️ Prerequisites

- **Java 17** or higher
- **Maven 3.8+**
- **Docker** (optional, for containerized deployment)

## 🏃 Quick Start

### Option 1: Run with Maven

```bash
# Clone the repository
git clone https://github.com/mrNiiAmatey/portfolio-springboot.git
cd portfolio-springboot

# Run the application
mvn spring-boot:run
```

Visit `http://localhost:8080` in your browser.

### Option 2: Run with Java

```bash
# Build the JAR
mvn clean package -DskipTests

# Run the JAR
java -jar target/portfolio-1.0.0.jar
```

### Option 3: Run with Docker

```bash
# Build the Docker image
docker build -t portfolio:latest .

# Run the container
docker run -p 8080:8080 portfolio:latest
```

## 🔌 API Endpoints

### Web Pages

| Endpoint | Description |
|----------|-------------|
| `GET /` | Main portfolio homepage |
| `GET /projects` | Projects page |
| `GET /about` | About/Resume page |
| `POST /contact` | Contact form submission |

### REST API

| Endpoint | Description |
|----------|-------------|
| `GET /api/v1/portfolio` | Complete portfolio data |
| `GET /api/v1/skills` | List of skills |
| `GET /api/v1/projects` | List of projects |
| `GET /api/v1/projects/{id}` | Specific project by ID |
| `GET /api/v1/experiences` | List of experiences |
| `GET /api/v1/health` | Health check |

### Example API Response

```bash
curl http://localhost:8080/api/v1/projects
```

```json
[
  {
    "id": "qr-code-generator",
    "title": "QR Code Generator API",
    "type": "Microservice",
    "description": "Production-ready Spring Boot REST API...",
    "technologies": ["Java 17", "Spring Boot", "ZXing", "JUnit"],
    "githubUrl": "https://github.com/mrNiiAmatey/QRCode-Generator",
    "icon": "🔲"
  }
]
```

## ⚙️ Configuration

### Application Properties

Edit `src/main/resources/application.properties`:

```properties
# Server port
server.port=8080

# Enable/disable Thymeleaf caching (disable for development)
spring.thymeleaf.cache=false

# Logging level
logging.level.com.niiamatey.portfolio=DEBUG
```

### Personal Information

Update your details in `PortfolioController.java`:

```java
model.addAttribute("name", "Your Name");
model.addAttribute("email", "your@email.com");
model.addAttribute("linkedin", "https://linkedin.com/in/your-profile");
model.addAttribute("github", "https://github.com/your-username");
```

### Adding Projects

Add new projects in `PortfolioDataConfig.java`:

```java
Project.builder()
    .id("my-new-project")
    .title("My New Project")
    .type("Full-Stack")
    .description("Description here...")
    .technologies(Arrays.asList("Java", "Spring Boot"))
    .githubUrl("https://github.com/user/repo")
    .icon("🚀")
    .build()
```

## 🧪 Testing

Run all tests:

```bash
mvn test
```

Run with coverage report:

```bash
mvn test jacoco:report
```

## 🚀 Deployment Options

### 1. Railway (Recommended for Java)

1. Create account at [railway.app](https://railway.app)
2. Connect your GitHub repository
3. Railway auto-detects Spring Boot and deploys

### 2. Render

1. Create account at [render.com](https://render.com)
2. New → Web Service → Connect repository
3. Build Command: `mvn clean package -DskipTests`
4. Start Command: `java -jar target/*.jar`

### 3. Heroku

```bash
# Install Heroku CLI, then:
heroku create your-portfolio-name
git push heroku main
```

### 4. AWS Elastic Beanstalk

1. Package: `mvn clean package`
2. Upload `target/portfolio-1.0.0.jar` to Elastic Beanstalk
3. Select Java 17 platform

### 5. Docker + Any Cloud

```bash
# Build and push to registry
docker build -t your-registry/portfolio:latest .
docker push your-registry/portfolio:latest

# Deploy to your preferred cloud (AWS ECS, Google Cloud Run, Azure Container Apps)
```

## 📊 Monitoring

Spring Actuator endpoints are available:

- `GET /actuator/health` - Application health status
- `GET /actuator/info` - Application information
- `GET /actuator/metrics` - Application metrics

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Commit changes: `git commit -m 'Add amazing feature'`
4. Push to branch: `git push origin feature/amazing-feature`
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👤 Author

**Nii Amatey Tagoe**

- GitHub: [@mrNiiAmatey](https://github.com/mrNiiAmatey)
- LinkedIn: [Your LinkedIn](https://linkedin.com/in/your-profile)

---

⭐ If this project helped you, please give it a star!
