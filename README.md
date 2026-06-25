# Spring AI Chatbot

A simple AI chatbot REST application built with Spring Boot 3.x and Spring AI, powered by OpenAI.

## 📋 Project Overview

This is a backend REST API that demonstrates how to integrate Spring AI with OpenAI to create a chatbot. Users can send questions through a REST endpoint and receive AI-generated answers.

### Technology Stack

- **Java**: 17
- **Spring Boot**: 3.2.5
- **Spring AI**: 0.8.1
- **Build Tool**: Maven
- **AI Provider**: OpenAI (GPT-3.5-Turbo)

## 🏗️ Project Structure

```
spring-ai-chatbot/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/springaichatbot/
│   │   │       ├── SpringAiChatbotApplication.java    # Main application class
│   │   │       ├── controller/
│   │   │       │   └── ChatController.java            # REST endpoints
│   │   │       ├── service/
│   │   │       │   └── ChatService.java               # Business logic
│   │   │       ├── dto/
│   │   │       │   ├── ChatRequest.java               # Request DTO
│   │   │       │   └── ChatResponse.java              # Response DTO
│   │   │       ├── config/
│   │   │       │   └── AIConfig.java                  # Spring AI configuration
│   │   │       └── exception/
│   │   │           ├── ErrorResponse.java             # Error response DTO
│   │   │           └── GlobalExceptionHandler.java    # Global exception handling
│   │   └── resources/
│   │       └── application.properties                 # Application configuration
│   └── test/
│       └── java/
└── pom.xml                                            # Maven configuration
```

## 🚀 Quick Start

### Prerequisites

- Java 17 or higher installed
- Maven 3.6+ installed
- OpenAI API key (get it from https://platform.openai.com/api-keys)

### Installation Steps

1. **Clone or Download the Project**
   ```bash
   cd spring-ai-chatbot
   ```

2. **Configure OpenAI API Key**

   You have two options to set your OpenAI API key:

   **Option A: Environment Variable (Recommended)**
   ```bash
   # Windows (PowerShell)
   $env:OPENAI_API_KEY = "your-openai-api-key-here"
   
   # Windows (Command Prompt)
   set OPENAI_API_KEY=your-openai-api-key-here
   
   # Linux/Mac
   export OPENAI_API_KEY="your-openai-api-key-here"
   ```

   **Option B: application.properties File**
   
   Edit `src/main/resources/application.properties` and replace:
   ```properties
   spring.ai.openai.api-key=your-openai-api-key-here
   ```

3. **Build the Project**
   ```bash
   mvn clean install
   ```

4. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

   Or build and run the JAR:
   ```bash
   mvn clean package
   java -jar target/spring-ai-chatbot-1.0.0.jar
   ```

5. **Verify the Application**
   
   The server should start on `http://localhost:8080`
   
   You should see logs like:
   ```
   Started SpringAiChatbotApplication in X.XXX seconds
   ```

## 📡 API Endpoints

### POST /api/chat

Send a message to the AI chatbot and receive a response.

**Request:**
```bash
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"Explain Apache Kafka"}'
```

**Request Body:**
```json
{
  "message": "Explain Apache Kafka"
}
```

**Response (200 OK):**
```json
{
  "answer": "Apache Kafka is a distributed streaming platform designed for building real-time data pipelines and streaming applications. It is a message broker that allows publishing, subscribing, and processing streams of records. Kafka is known for its high throughput, low latency, and scalability..."
}
```

**Error Response (500 Internal Server Error):**
```json
{
  "error": "Internal Server Error",
  "message": "Failed to get response from AI model: API key not set",
  "timestamp": 1234567890000
}
```

## 🔧 Configuration

Edit `src/main/resources/application.properties` to customize:

```properties
# Server port (default: 8080)
server.port=8080

# OpenAI API Key (required)
spring.ai.openai.api-key=your-api-key-here

# AI Model (options: gpt-3.5-turbo, gpt-4, gpt-4-turbo, etc.)
spring.ai.openai.chat.options.model=gpt-3.5-turbo

# Temperature (0.0-2.0, higher = more creative)
spring.ai.openai.chat.options.temperature=0.7

# Logging Level
logging.level.com.example.springaichatbot=DEBUG
```

## 🧪 Testing the API

### Using cURL

```bash
# Simple question
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"What is machine learning?"}'

# Another example
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"How does Spring Boot work?"}'
```

### Using Postman

1. Create a new POST request
2. URL: `http://localhost:8080/api/chat`
3. Headers: `Content-Type: application/json`
4. Body (raw JSON):
   ```json
   {
     "message": "Your question here"
   }
   ```
5. Click Send

### Using VS Code REST Client Extension

Create a file `test.http`:
```http
POST http://localhost:8080/api/chat
Content-Type: application/json

{
  "message": "Explain REST APIs"
}
```

## 📚 Code Overview

### ChatController.java
Handles HTTP requests and routes them to the service layer:
- `POST /api/chat` - Accepts chat messages and returns AI responses

### ChatService.java
Contains the business logic for interacting with the AI model:
- `chat(String message)` - Sends message to OpenAI and returns response

### AIConfig.java
Spring configuration for AI components:
- Configures `ChatClient` bean for AI communication

### DTOs
- `ChatRequest` - Contains user's message
- `ChatResponse` - Contains AI's answer

### Exception Handling
- `GlobalExceptionHandler` - Centralized exception handling
- `ErrorResponse` - Standardized error response format

## 🔐 Security Considerations

1. **Never commit your API key** to version control
2. Always use environment variables for sensitive data
3. Consider using Spring Vault for production deployments
4. Implement rate limiting for production APIs
5. Add authentication/authorization as needed

## 📝 Logs

Application logs will show:
- Incoming chat requests
- AI model responses
- Any errors or exceptions

Example:
```
2024-06-25 10:30:45 - c.e.s.controller.ChatController - Chat request received with message: Explain Kafka
2024-06-25 10:30:46 - c.e.s.service.ChatService - Received chat message: Explain Kafka
2024-06-25 10:30:48 - c.e.s.service.ChatService - AI response: Kafka is a distributed event streaming platform...
```

## 🛠️ Troubleshooting

### Issue: "API key not set" error

**Solution:** Make sure to set the `OPENAI_API_KEY` environment variable or update `application.properties`

### Issue: "Connection refused" to OpenAI

**Solution:** 
- Check your internet connection
- Verify your API key is valid
- Check OpenAI service status at https://status.openai.com/

### Issue: Slow responses

**Solution:**
- Large responses may take time
- Check your network connectivity
- Consider adjusting the `temperature` setting

### Issue: Build fails

**Solution:**
- Ensure you have Java 17+ installed: `java -version`
- Update Maven: `mvn -v`
- Clear Maven cache: `mvn clean`

## 📦 Maven Dependencies

Key dependencies included:
- `spring-boot-starter-web` - Web framework
- `spring-ai-openai-spring-boot-starter` - Spring AI + OpenAI integration
- `lombok` - Reduces boilerplate code
- `spring-boot-devtools` - Development utilities

## 🤝 Extending the Application

### Add additional endpoints:
```java
@GetMapping("/health")
public ResponseEntity<String> health() {
    return ResponseEntity.ok("Chatbot is running!");
}
```

### Add request validation:
```java
@PostMapping
public ResponseEntity<ChatResponse> chat(@Valid @RequestBody ChatRequest request) {
    // validation happens automatically
}
```

### Add conversation history:
Store messages in a database and pass context to the AI model.

## 📄 License

This project is open source and available for educational purposes.

## 🤖 OpenAI Models

You can use different OpenAI models by changing the configuration:
- `gpt-3.5-turbo` (Fast, cost-effective)
- `gpt-4` (More powerful, higher cost)
- `gpt-4-turbo-preview` (Latest features, higher cost)

## 📞 Support

For issues or questions:
1. Check the logs: `logging.level.com.example.springaichatbot=DEBUG`
2. Verify API key is correct
3. Check OpenAI API documentation
4. Verify firewall/proxy settings

---

**Happy Coding! 🚀**

