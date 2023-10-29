# ISBN-validator

This ISBN validator takes in a string and checks whether the string is a valid ISBN.

It is a webservice using Spring Boot. 

The application can be started with command mvn spring-boot:run in the terminal.

Or if it's opened in intelliJ, with shift+F10.

There are several ways the application can be tested.
Most convenient is just to use cURL.

Here are some examples to try:

curl -X POST
http://localhost:8080/api/ISBNValidation/043942089X

http://localhost:8080/api/ISBNValidation/121212082X

http://localhost:8080/api/ISBNValidation/HereAreTen

http://localhost:8080/api/ISBNValidation/ThirteenThirt
