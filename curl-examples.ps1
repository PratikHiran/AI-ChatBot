# Spring AI Chatbot - PowerShell API Test Examples

# Test 1: Simple question about Kafka
Invoke-WebRequest -Uri "http://localhost:8080/api/chat" `
  -Method POST `
  -Headers @{"Content-Type"="application/json"} `
  -Body '{"message":"Explain Apache Kafka in simple terms"}'

# Test 2: Question about Machine Learning
Invoke-WebRequest -Uri "http://localhost:8080/api/chat" `
  -Method POST `
  -Headers @{"Content-Type"="application/json"} `
  -Body '{"message":"What is machine learning and how is it different from deep learning?"}'

# Test 3: Question about Spring Boot
Invoke-WebRequest -Uri "http://localhost:8080/api/chat" `
  -Method POST `
  -Headers @{"Content-Type"="application/json"} `
  -Body '{"message":"How does Spring Boot simplify Java development?"}'

# Test 4: Question about REST APIs
Invoke-WebRequest -Uri "http://localhost:8080/api/chat" `
  -Method POST `
  -Headers @{"Content-Type"="application/json"} `
  -Body '{"message":"What are the best practices for designing REST APIs?"}'

# Test 5: Question about Microservices
Invoke-WebRequest -Uri "http://localhost:8080/api/chat" `
  -Method POST `
  -Headers @{"Content-Type"="application/json"} `
  -Body '{"message":"What are the advantages and disadvantages of microservices architecture?"}'

# Test 6: Code example request
Invoke-WebRequest -Uri "http://localhost:8080/api/chat" `
  -Method POST `
  -Headers @{"Content-Type"="application/json"} `
  -Body '{"message":"Write a simple Python example to read a CSV file"}'

# Test 7: Longer conversation context
Invoke-WebRequest -Uri "http://localhost:8080/api/chat" `
  -Method POST `
  -Headers @{"Content-Type"="application/json"} `
  -Body '{"message":"I am learning Spring Boot. Can you explain how dependency injection works?"}'

