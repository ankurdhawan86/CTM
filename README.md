# CTM
Spring-boot project for compare the market for credit card number validation.

The application creates an endpoint which takes a credit card number as a query parameter and checks if the number is a valid credit card or not by using Luhn's algorithm.
Prerequisites:
Maven
Java 1.8 or higher

To build the application simply clone the repo into your desktop and run the below. For the porpose we will take the folder to be "C:\code\CTM" where application is cloned. You need to change this accordingly for linux and mac.

- Open any CLI like powershell or console.
- navigate to folder C:\code\CTM\cc-validator
- mvn clean install (This step will download all dependencies)
- mvn spring-boot:run (This will start the application)


If the file has been trasferred as zip file, then floow the below steps:
- extract zip to a folder "C:\code"
- Open any CLI like powershell or console.
- Go to C:\code\CTM\cc-validator
- mvn clean install (This step will download all dependencies)
- mvn spring-boot:run (This will start the application)

API documentation can be viewed on:
http://localhost:8080/swagger-ui/index.html


Once the application has started successfully, you can Open a browser and can test the API using below URI:

http://localhost:8080/api/validateCreditCard?cardNumber=xxxxxxxxxxx


 
