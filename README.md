
# Start Jenkins
```bash
docker run -d -p 8080:8080 -p 50000:50000 -v /Users/jjiang153/Documents/Playground/caches/jenkinshome:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock jenkins/jenkins:lts
```	

# Install Blue Ocean