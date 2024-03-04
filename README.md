# trading-app-test

## Quick summary:
This is a project for testing a simple Trading service.

## Required tools and libraries
To run project please install:
1. [JDK 17](https://www.oracle.com/java/technologies/downloads/#JDK17)
2. [Maven 3.9.6](https://maven.apache.org/download.cgi)
3. Your service should be up and running on port `8080` (by default, tests are executed against: http://localhost:8080)

## Build code
Execute in a shell
```shell
git clone https://github.com/litvin88/trading-app-test.git
cd trading-app-test
mvn -U clean verify -DskipTests
```

## Test execution and reports:
```shell
mvn test
```
After tests were finished you can find HTML and JSON reports under `target/reports/`