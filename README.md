# Spring Authentication with MetaMask
The project shows how to create a telegram bot and implement asymmetric authentication with the Phantom extension in a Spring application.

## Login flow

<img src="https://dz2cdn1.dzone.com/storage/temp/17180912-spring-authentication-with-metamask.png" height="600"/>

1. Connect to Phantom and receive user’s address.
2. Obtain a one-time code (nonce) for the user.
3. Using Phantom, sign a message containing a nonce with the user's private key.
4. Authenticate user by validating the user's signature on the back end.
5. Generate a new nonce to prevent the user's signature from being compromised.

---

## Getting started

1. Install [Phantom extension] and create a wallet
2. Build and launch the application:
```
mvn package && java -jar target/*.jar
```
3. Open [localhost:8080](http://localhost:8080)
