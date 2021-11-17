# Cryptoboard Portfolio Service

## Description
With this API a registered user can track his crypto portfolio.

## Run the application
```
mvn spring-boot:run
```

## Endpoints
With this endpoint all the coins in a portfolio from a specific user is retrieved.

GET: ```api/v1/private/portfolio/{userId}```

With this endpoint a coin is deleted from a portfolio and the value is added to account credit.

DELETE: ```api/v1/private/portfolio/{userId}/{coinId}```

## Security
Endpoints are secured and can only be accessed with JWT tokens.
JWT tokens are send from [SPA](https://github.com/DirkLemmen/CryptoBoardSPA)

## Questions
Send me an email if you have questions: dirk.lemmen2001@gmail.com

