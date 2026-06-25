# Setup Instructions - Spring AI Chatbot

Detailed setup instructions for different operating systems.

## Prerequisites Check

Before starting, verify you have the required tools installed:

### Check Java Version
```bash
# All platforms
java -version

# Expected output should show Java 17 or higher
# Example: openjdk version "17.0.8" 2023-07-18
```

### Check Maven Version
```bash
# All platforms
mvn -v

# Expected output should show Maven 3.6 or higher
# Example: Apache Maven 3.9.0 (...)
```

If either is not installed, see the installation sections below.

---

## Windows Setup

### 1. Install Java 17

**Option A: Using Chocolatey (Recommended)**
```powershell
# Install Chocolatey if you don't have it
# Run PowerShell as Administrator

choco install openjdk17

# Verify installation
java -version
```

**Option B: Manual Download**
- Visit: https://adoptium.net/
- Download OpenJDK 17 (LTS) for Windows
- Run the installer and follow the prompts
- During installation, check "Set JAVA_HOME"

### 2. Install Maven

**Option A: Using Chocolatey**
```powershell
choco install maven

# Verify installation
mvn -v
```

**Option B: Manual Install**
- Visit: https://maven.apache.org/download.cgi
- Download Binary zip archive
- Extract to a folder (e.g., `C:\Tools\apache-maven-3.9.0`)
- Add to PATH:
  1. Open Environment Variables (Win+X → System → Advanced → Environment Variables)
  2. Add `C:\Tools\apache-maven-3.9.0\bin` to System PATH
  3. Restart PowerShell

### 3. Get OpenAI API Key

1. Visit: https://platform.openai.com/api-keys
2. Sign up or log in to your OpenAI account
3. Click "Create new secret key"
4. Copy and save the key safely

### 4. Set Environment Variable

```powershell
# Run as Administrator
[Environment]::SetEnvironmentVariable("OPENAI_API_KEY", "your-api-key-here", "User")

# Verify it's set (restart PowerShell after setting)
$env:OPENAI_API_KEY
```

### 5. Build and Run

```powershell
# Navigate to project directory
cd "D:\New folder"

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

# Alternative: Build JAR and run
mvn clean package
java -jar target/spring-ai-chatbot-1.0.0.jar
```

---

## macOS Setup

### 1. Install Java 17

**Using Homebrew (Recommended)**
```bash
# Install Homebrew if you don't have it
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# Install Java 17
brew install openjdk@17

# Set JAVA_HOME (add to ~/.zshrc or ~/.bash_profile)
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 17)' >> ~/.zshrc
source ~/.zshrc

# Verify
java -version
```

### 2. Install Maven

```bash
brew install maven

# Verify
mvn -v
```

### 3. Get OpenAI API Key

1. Visit: https://platform.openai.com/api-keys
2. Sign up or log in to your OpenAI account
3. Click "Create new secret key"
4. Copy and save the key safely

### 4. Set Environment Variable

```bash
# Add to ~/.zshrc or ~/.bash_profile
echo 'export OPENAI_API_KEY="your-api-key-here"' >> ~/.zshrc
source ~/.zshrc

# Verify
echo $OPENAI_API_KEY
```

### 5. Build and Run

```bash
# Navigate to project directory
cd /path/to/spring-ai-chatbot

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

# Alternative: Build JAR and run
mvn clean package
java -jar target/spring-ai-chatbot-1.0.0.jar
```

---

## Linux (Ubuntu/Debian) Setup

### 1. Install Java 17

```bash
# Update package manager
sudo apt update

# Install Java 17
sudo apt install openjdk-17-jdk

# Verify
java -version

# Set JAVA_HOME (add to ~/.bashrc)
echo 'export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64' >> ~/.bashrc
source ~/.bashrc
```

### 2. Install Maven

```bash
# Install Maven
sudo apt install maven

# Verify
mvn -v
```

### 3. Get OpenAI API Key

1. Visit: https://platform.openai.com/api-keys
2. Sign up or log in to your OpenAI account
3. Click "Create new secret key"
4. Copy and save the key safely

### 4. Set Environment Variable

```bash
# Add to ~/.bashrc
echo 'export OPENAI_API_KEY="your-api-key-here"' >> ~/.bashrc
source ~/.bashrc

# Verify
echo $OPENAI_API_KEY
```

### 5. Build and Run

```bash
# Navigate to project directory
cd /path/to/spring-ai-chatbot

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

# Alternative: Build JAR and run
mvn clean package
java -jar target/spring-ai-chatbot-1.0.0.jar
```

---

## Docker Setup (Optional)

If you want to run the application in Docker:

### 1. Create Dockerfile

```dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/spring-ai-chatbot-1.0.0.jar app.jar

ENV OPENAI_API_KEY=${OPENAI_API_KEY}

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

### 2. Build and Run Docker Image

```bash
# Build the JAR first
mvn clean package

# Build Docker image
docker build -t spring-ai-chatbot:1.0.0 .

# Run Docker container
docker run -e OPENAI_API_KEY="your-api-key-here" -p 8080:8080 spring-ai-chatbot:1.0.0
```

---

## Troubleshooting

### Issue: "Java not found"

```bash
# Windows PowerShell
where java

# macOS/Linux
which java
```

If not found, verify Java installation and JAVA_HOME environment variable.

### Issue: "Maven not found"

```bash
# Windows PowerShell
where mvn

# macOS/Linux
which mvn
```

If not found, verify Maven installation and PATH environment variable.

### Issue: "OPENAI_API_KEY not found"

```bash
# Windows PowerShell
$env:OPENAI_API_KEY

# macOS/Linux
echo $OPENAI_API_KEY
```

If empty, verify you've set the environment variable correctly.

### Issue: "Connection refused"

Make sure the application is running on port 8080 before making API calls.

### Issue: Maven downloads taking forever

This is normal for the first build. Maven is downloading dependencies. Subsequent builds will be faster.

### Issue: "The AI model returned an error"

- Verify your API key is correct
- Check your OpenAI account has credits
- Verify your network connection
- Check OpenAI service status: https://status.openai.com/

---

## Verify Installation

After setup, verify everything works:

```bash
# 1. Build the project
mvn clean install

# 2. Start the application
mvn spring-boot:run

# 3. In a new terminal/PowerShell, test the API
# Windows PowerShell
Invoke-WebRequest -Uri "http://localhost:8080/api/chat" `
  -Method POST `
  -Headers @{"Content-Type"="application/json"} `
  -Body '{"message":"Hello, how are you?"}'

# macOS/Linux with curl
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"Hello, how are you?"}'
```

You should receive a JSON response with the AI's answer.

---

## Next Steps

- Read the main README.md for API documentation
- Customize configuration in application.properties
- Explore the source code in src/main/java
- Test different questions and AI models
- Deploy to your preferred cloud platform

Good luck! 🚀

