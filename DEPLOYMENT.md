# Deployment Guide - Spring AI Chatbot

This guide covers deploying the Spring AI Chatbot to various cloud platforms.

## Table of Contents
1. [Heroku](#heroku)
2. [AWS](#aws-elastic-beanstalk)
3. [Azure](#azure-app-service)
4. [Google Cloud Platform](#google-cloud-platform)
5. [Docker](#docker)

---

## Heroku

### Prerequisites
- Heroku account: https://www.heroku.com/
- Heroku CLI installed: https://devcenter.heroku.com/articles/heroku-cli
- Git installed

### Steps

1. **Create Heroku App**
   ```bash
   heroku login
   heroku create spring-ai-chatbot
   ```

2. **Add OpenAI API Key**
   ```bash
   heroku config:set OPENAI_API_KEY="your-api-key-here"
   ```

3. **Create Procfile**
   ```bash
   echo "web: java -jar target/spring-ai-chatbot-1.0.0.jar" > Procfile
   ```

4. **Create system.properties**
   ```bash
   echo "java.runtime.version=17" > system.properties
   ```

5. **Deploy**
   ```bash
   git add .
   git commit -m "Deploy to Heroku"
   git push heroku main
   ```

6. **Verify**
   ```bash
   heroku open
   # Test endpoint
   curl -X POST https://your-app.herokuapp.com/api/chat \
     -H "Content-Type: application/json" \
     -d '{"message":"Hello"}'
   ```

---

## AWS Elastic Beanstalk

### Prerequisites
- AWS account
- AWS CLI installed
- Elastic Beanstalk CLI installed

### Steps

1. **Build the JAR**
   ```bash
   mvn clean package
   ```

2. **Initialize Elastic Beanstalk**
   ```bash
   eb init -p java-17 spring-ai-chatbot
   ```

3. **Create Environment**
   ```bash
   eb create spring-ai-chatbot-env
   ```

4. **Set Environment Variables**
   ```bash
   eb setenv OPENAI_API_KEY="your-api-key-here"
   ```

5. **Deploy**
   ```bash
   eb deploy
   ```

6. **Monitor**
   ```bash
   eb logs
   eb status
   ```

### Using CloudFormation (Optional)

Create a `cloudformation.yaml` file and deploy with:
```bash
aws cloudformation create-stack \
  --stack-name spring-ai-chatbot \
  --template-body file://cloudformation.yaml
```

---

## Azure App Service

### Prerequisites
- Azure account
- Azure CLI installed

### Steps

1. **Build the JAR**
   ```bash
   mvn clean package
   ```

2. **Create Resource Group**
   ```bash
   az group create --name spring-ai-rg --location eastus
   ```

3. **Create App Service Plan**
   ```bash
   az appservice plan create --name spring-ai-plan \
     --resource-group spring-ai-rg \
     --sku B2 --is-linux
   ```

4. **Create Web App**
   ```bash
   az webapp create --resource-group spring-ai-rg \
     --plan spring-ai-plan \
     --name spring-ai-chatbot \
     --runtime "JAVA|17"
   ```

5. **Deploy JAR**
   ```bash
   az webapp up --resource-group spring-ai-rg \
     --name spring-ai-chatbot \
     --plan spring-ai-plan
   ```

6. **Set Configuration**
   ```bash
   az webapp config appsettings set \
     --resource-group spring-ai-rg \
     --name spring-ai-chatbot \
     --settings OPENAI_API_KEY="your-api-key-here"
   ```

---

## Google Cloud Platform

### Prerequisites
- GCP account
- Google Cloud SDK installed
- `gcloud` CLI configured

### Steps

1. **Build the JAR**
   ```bash
   mvn clean package
   ```

2. **Create Google Cloud App**
   ```bash
   gcloud app create --region=us-central
   ```

3. **Create app.yaml**
   ```yaml
   runtime: java17
   
   env: standard
   
   entrypoint: "java -jar target/spring-ai-chatbot-1.0.0.jar"
   
   env_variables:
     OPENAI_API_KEY: "your-api-key-here"
   ```

4. **Deploy**
   ```bash
   gcloud app deploy
   ```

5. **View Logs**
   ```bash
   gcloud app logs read -f
   ```

### Using Cloud Run (Recommended)

1. **Create Dockerfile** (provided in project)

2. **Build Image**
   ```bash
   gcloud builds submit --tag gcr.io/PROJECT_ID/spring-ai-chatbot
   ```

3. **Deploy to Cloud Run**
   ```bash
   gcloud run deploy spring-ai-chatbot \
     --image gcr.io/PROJECT_ID/spring-ai-chatbot \
     --platform managed \
     --region us-central1 \
     --set-env-vars OPENAI_API_KEY="your-api-key-here" \
     --allow-unauthenticated
   ```

---

## Docker

### Build Docker Image

```bash
# Build JAR first
mvn clean package

# Build Docker image
docker build -t spring-ai-chatbot:1.0.0 .

# Run locally
docker run -e OPENAI_API_KEY="your-api-key-here" \
  -p 8080:8080 \
  spring-ai-chatbot:1.0.0
```

### Push to Docker Registry

```bash
# Docker Hub
docker tag spring-ai-chatbot:1.0.0 yourusername/spring-ai-chatbot:1.0.0
docker push yourusername/spring-ai-chatbot:1.0.0

# Docker Hub - Run
docker run -e OPENAI_API_KEY="your-api-key-here" \
  -p 8080:8080 \
  yourusername/spring-ai-chatbot:1.0.0
```

### Kubernetes Deployment

Create `deployment.yaml`:
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: spring-ai-chatbot
spec:
  replicas: 2
  selector:
    matchLabels:
      app: spring-ai-chatbot
  template:
    metadata:
      labels:
        app: spring-ai-chatbot
    spec:
      containers:
      - name: spring-ai-chatbot
        image: yourusername/spring-ai-chatbot:1.0.0
        ports:
        - containerPort: 8080
        env:
        - name: OPENAI_API_KEY
          valueFrom:
            secretKeyRef:
              name: openai-secret
              key: api-key
```

Deploy:
```bash
# Create secret
kubectl create secret generic openai-secret \
  --from-literal=api-key="your-api-key-here"

# Deploy application
kubectl apply -f deployment.yaml

# Expose service
kubectl expose deployment spring-ai-chatbot \
  --port=8080 \
  --type=LoadBalancer
```

---

## Configuration for Production

### Security Best Practices

1. **Use Secrets Management**
   - Store API keys in cloud provider's secret manager
   - Never hardcode secrets in code

2. **Enable HTTPS**
   - Use SSL certificates
   - Force HTTPS redirection

3. **Rate Limiting**
   - Implement API rate limiting
   - Consider using API Gateway

4. **Authentication**
   - Add JWT or OAuth2 authentication
   - Restrict API access

5. **Logging & Monitoring**
   - Enable application logging
   - Set up monitoring and alerts
   - Use centralized log aggregation

### Performance Optimization

1. **Caching**
   ```properties
   spring.cache.type=redis
   ```

2. **Database Connection Pooling**
   ```properties
   spring.datasource.hikari.maximum-pool-size=20
   ```

3. **Response Compression**
   ```properties
   server.compression.enabled=true
   ```

### Scaling

- Use load balancers to distribute traffic
- Implement horizontal scaling
- Use CDN for static content
- Monitor and auto-scale based on metrics

---

## Monitoring & Maintenance

### Application Monitoring
- Use Application Performance Monitoring (APM) tools
- Set up alerts for errors and high latency
- Monitor API usage and costs

### Log Analysis
- Aggregate logs from all instances
- Set up alerts for error patterns
- Regular log review and cleanup

### Updates
- Keep Spring Boot and Spring AI updated
- Update dependencies regularly
- Test updates in staging first

---

## Troubleshooting Deployment

### Issue: Application won't start

```bash
# Check logs
heroku logs --tail        # Heroku
eb logs                   # AWS
az webapp log tail        # Azure
gcloud app logs read -f   # GCP
```

### Issue: API key not recognized

- Verify environment variable name: `OPENAI_API_KEY`
- Ensure variable is set before application startup
- Restart application after setting variables

### Issue: High latency

- Check OpenAI API status
- Monitor network connectivity
- Consider using regional endpoints
- Implement caching where possible

### Issue: Cost concerns

- Monitor API usage
- Implement rate limiting
- Use GPT-3.5-Turbo instead of GPT-4 when possible
- Set usage alerts with cloud provider

---

## Rollback Procedures

### Heroku
```bash
heroku releases
heroku rollback
```

### AWS
```bash
eb history
eb setenv APP_VERSION=previous-version
eb deploy
```

### Azure
```bash
az webapp up --resource-group spring-ai-rg
```

### GCP
```bash
gcloud app versions list
gcloud app traffic-split
```

---

## Continuous Deployment (CI/CD)

### GitHub Actions Example

Create `.github/workflows/deploy.yml`:
```yaml
name: Deploy to Heroku

on:
  push:
    branches: [main]

jobs:
  deploy:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up Java
        uses: actions/setup-java@v2
        with:
          java-version: '17'
      - name: Build with Maven
        run: mvn clean package
      - name: Deploy to Heroku
        env:
          HEROKU_API_KEY: ${{ secrets.HEROKU_API_KEY }}
        run: |
          git remote add heroku https://git.heroku.com/spring-ai-chatbot.git
          git push heroku main
```

---

## Support & Resources

- Spring Boot Documentation: https://spring.io/projects/spring-boot
- Spring AI Documentation: https://spring.io/projects/spring-ai
- OpenAI API Documentation: https://platform.openai.com/docs

---

**Happy Deploying! 🚀**

