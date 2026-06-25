# Spring AI Chatbot - cURL API Test Examples

# Test 1: Simple question about Kafka
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"Explain Apache Kafka in simple terms"}'

# Test 2: Question about Machine Learning
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"What is machine learning and how is it different from deep learning?"}'

# Test 3: Question about Spring Boot
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"How does Spring Boot simplify Java development?"}'

# Test 4: Question about REST APIs
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"What are the best practices for designing REST APIs?"}'

# Test 5: Question about Microservices
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"What are the advantages and disadvantages of microservices architecture?"}'

# Test 6: Code example request
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"Write a simple Python example to read a CSV file"}'

# Test 7: Longer conversation context
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"I am learning Spring Boot. Can you explain how dependency injection works?"}'

