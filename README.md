
# PeetBetBot(Telegram Bot) and Spring Authentication with Phantom

PeetBetBot is a Telegram bot that facilitates betting games using PEET Coin. The bot manages wallet connections, handles betting transactions, and automates the betting process while ensuring security through multi-signature wallets.

## Login flow

<img src="https://dz2cdn1.dzone.com/storage/temp/17180912-spring-authentication-with-metamask.png" height="600"/>

1. Connect to Phantom and receive user’s address.
2. Obtain a one-time code (nonce) for the user.
3. Using Phantom, sign a message containing a nonce with the user's private key.
4. Authenticate user by validating the user's signature on the back end.
5. Generate a new nonce to prevent the user's signature from being compromised.

## Features

- **Group Integration:** Add PeetBetBot to your Telegram group for seamless interaction.
- **Wallet Connection:** Connect user wallets using Wallet Connect API for authorization.
- **Automated Transactions:** Calculate and transfer a percentage of the total betting pot to a multi-signature wallet.
- **Solana Conversion:** Automatically convert funds to Solana and transfer to the multi-signature wallet.
- **Real-Time Betting:** Users can place bets, play games, and receive immediate transaction confirmations.
- **Result Announcement:** The bot announces the betting pot amount and results in the group chat.
## Technologies Used

- **Java:** Core backend language

- **Spring Boot:** Framework for building the backend and user management system.

- **Telegram Bot API:** Library for creating and managing Telegram bots.

- **Wallet Connect API:** Used for connecting and authorizing user wallets.

- **Solana:** Cryptocurrency used for transaction processing.

## Getting Started

**Prerequisites**

- Java 17 or higher
- Maven
- Telegram Bot API token
- Wallet Connect API credentials
- Multi-Signature Wallet setup
- Phantom Wallet

**Installation**

**1) Clone the repository:**

```bash
  git clone https://github.com/Surajrawat1709/Peetbetbot.git
  cd peetbetbot

```
**2) Backend Setup:**
  - Navigate to the backend directory:
    ```bash
      cd backend
    ```
  - Update the application.properties file with your Telegram Bot API token, Wallet Connect API credentials, and other necessary configurations.

  - Build the project using Maven:
     ```bash
       mvn clean install
    ```
   - Run the Spring Boot application:
     ```bash
       mvn spring-boot:run
      ```

    ``` 
**3) Telegram Bot Setup:**
  - Create a new bot using BotFather on Telegram and obtain the API token.
- Update the application properties with your Telegram Bot API token.
## Usage/Workflow


**1) Add PeetBetBot to the Group:**

- Add the bot to your Telegram group.
- The bot will send a message: "Please Wait... Inviting Peet Bet Assistant to your chat".

**2) Start the Betting Process:**

- The bot will ask for the coin address (CA) for betting and the amount for the betting pot.
- Options for the betting pot:
  - $10
  -  $50
    - $100
    - $500
    - $1000


**3) Wallet Connection and Authorization:**

- Connect your wallet using the Wallet Connect API for authorization.
- The bot calculates 5% of the total and transfers the money to a multi-signature wallet.
- Funds are converted to Solana and transferred to the multi-signature wallet.
- Confirm the transaction.

**4) Announce Betting Pot:**

- Once the transaction is confirmed, the bot announces in the group that PeetBetBot has been initiated with the total pot amount.

**5) Place Bets:**

- Users can type /bet to start betting.
- Select the game from a list.
- Enter the amount to bet in PEET (cannot be more than a certain percentage of the pot).
- Connect Peet wallet address to place the bet.

**6) Game Play:**

- The bot presents game questions and users provide answers.
- The bot determines if the user wins or loses.
- If the user wins, the bot initiates a transaction from the betting pot to the user's wallet, including earnings.
- If the user loses, the user's bet is transferred to the betting wallet.
- The bot announces the result and the current pot amount after each bet.


