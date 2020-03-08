# Jenkins Play

## Jenkins Dockerfile

[Jenkins With Docker](jenkinsImg/README.md)

## Start Jenkins

### Start with Jenkins home mounted

```bash
docker run -d -p 8080:8080 -p 50000:50000 -v /Users/jjiang153/Documents/Playground/caches/jenkinshome:/var/jenkins_home my-docker-jenkins
```

### Start With Docker mounted

```bash
docker run -d -p 8080:8080 -p 50000:50000 -v /Users/jjiang153/Documents/Playground/caches/jenkinshome:/var/jenkins_home my-docker-jenkins
```

## Table of contents

* [Pipeline&Job-DSL](pipeline/README.md)