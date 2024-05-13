Run test -
export ENVIRONMENT=production.org 
./gradlew loginTests


parameters:
browser - chrome or firefox. default - chrome
headless - 0 or 1. default - 0
Generate allure report - allure serve target/allure-results