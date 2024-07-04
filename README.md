# Spring Authentication with MetaMask
The project shows how to implement asymmetric authentication with the MetaMask extension in a Spring application.

## Login flow

<img src="https://dz2cdn1.dzone.com/storage/temp/17180912-spring-authentication-with-metamask.png" height="600"/>

1. Connect to MetaMask and receive user’s address.
2. Obtain a one-time code (nonce) for the user.
3. Using MetaMask, sign a message containing a nonce with the user's private key.
4. Authenticate user by validating the user's signature on the back end.
5. Generate a new nonce to prevent the user's signature from being compromised.

Check out more details about implementation: https://dzone.com/articles/spring-authentication-with-metamask

---

## Getting started

1. Install [MetaMask extension](https://metamask.io/) and create a wallet
2. Build and launch the application:
```
mvn package && java -jar target/*.jar
```
3. Open [localhost:8080](http://localhost:8080)
