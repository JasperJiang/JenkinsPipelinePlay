# Jenkins Play

## Jenkins Dockerfile

[Jenkins With Docker](jenkinsImg/README.md)

## Start Jenkins

### Start with Jenkins home mounted

```bash
docker run -d -p 8080:8080 -p 50000:50000 -v /Users/jjiang153/Documents/Playground/caches/jenkinshome:/var/jenkins_home my-docker-jenkins
```

docker run -d -p 8080:8080 -p 50000:50000 -v /home/jack/jenkins_home:/var/jenkins_home jasperjiang/jenkins_docker

### Start With Docker mounted

```bash
docker run -d -p 8080:8080 -p 50000:50000 -v /Users/jjiang153/Documents/Playground/caches/jenkinshome:/var/jenkins_home my-docker-jenkins
```

## Table of contents

* [Pipeline](pipeline/README.md)
* [Job-DSL](jobDsl/README.md)
* [github Pull Request Builder](https://github.com/jenkinsci/ghprb-plugin/blob/master/README.md)