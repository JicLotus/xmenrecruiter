<img src="https://travis-ci.org/JicLotus/xmenrecruiter.svg?branch=master">

# X-Men Recruiter

<p align="center">
  <img width="100" height="100" src="https://github.com/JicLotus/xmenrecruiter/blob/master/xmenImage.jpg">
</p>

# Get Started:

- Install Maven
- Install Java 8

# Execution:

- mvn compile
- mvn package
- mvn exec:java -Dexec.mainClass="main.java.com.testng.apiRest"

# Docker

- Install Docker
- Go into xmenrecruiter/Dockerfile respository folder
- Execute: sudo docker build -t xmenrecruiter .
- Execute: sudo docker run -t -i xmenrecruiter xmenrecruiter
- Execute if you want to start spark api/rest : mvn exec:java -Dexec.mainClass="main.java.com.testng.apiRest"
- Execute if you want to start running tests: mvn -Dtest:test.java.com.testng.CoreTest test


