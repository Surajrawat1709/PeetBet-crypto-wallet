package com.example.bot;

import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
//import com.example.springauthenticationwithmetamask.*;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import static com.example.bot.Constants.*;
import static com.example.bot.UserState.*;

public class ResponseHandler {
    private final SilentSender sender;
    private final Map<Long, UserState> chatStates;

    // WebController webController =new WebController();
    public ResponseHandler(SilentSender sender, DBContext db) {
        this.sender = sender;
        chatStates = db.getMap(Constants.CHAT_STATES);
    }

    public void replyToStart(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(START_TEXT);
        sender.execute(message);
        chatStates.put(chatId, AWAITING_NAME);
    }

    public void replyToButtons(long chatId, Message message) throws InterruptedException {
        if (message.getText().equalsIgnoreCase("/stop")) {
            stopChat(chatId);
        }

        switch (chatStates.get(chatId)) {
            case AWAITING_NAME -> replyToName(chatId, message);
            case FOOD_DRINK_SELECTION -> replyToFoodDrinkSelection(chatId, message);
           // case PIZZA_TOPPINGS -> replyToPizzaToppings(chatId, message);
            case AWAITING_CONFIRMATION -> replyToOrder(chatId, message);
            case GAMES_TO_PLAY -> selectGamesForPlaying(chatId, message);
            case AMOUNT_TO_BET -> selectAMTTobet(chatId, message);
            case USER_WON_LOST -> userWonOrLost(chatId, message);
            case AMOUNT_IN_POT -> amountInPot(chatId, message);
            default -> unexpectedMessage(chatId);
        }
    }

    private void unexpectedMessage(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText("I did not expect that.");
        sender.execute(sendMessage);
    }

    private void stopChat(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText("Thank you for playing. See you soon!\nPress /start to order again");
        chatStates.remove(chatId);
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        sender.execute(sendMessage);
    }

    private void replyToOrder(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        if ("yes".equalsIgnoreCase(message.getText())) {
            sendMessage.setText("Nice, let's play. Thank you!\n Select the coin");
            sendMessage.setReplyMarkup(KeyboardFactory.getPizzaOrDrinkKeyboard());
            sender.execute(sendMessage);
            chatStates.put(chatId, FOOD_DRINK_SELECTION);
        } else if ("no".equalsIgnoreCase(message.getText())) {
            stopChat(chatId);
        } else {
            sendMessage.setText("Please select yes or no");
            sendMessage.setReplyMarkup(KeyboardFactory.getYesOrNo());
            sender.execute(sendMessage);
        }
    }

    private void replyToFoodDrinkSelection(long chatId, Message message) {
        // URL to the accountInfo.html page on localhost
        String url = "https://yourdomain.com/login"; // Replace with your actual URL

        // Create the message text with HTML formatting
      //  String messageText = "Please <a href=\"" + url + "\">click here to login</a>.";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        if ("Other Coin".equalsIgnoreCase(message.getText())) {
            sendMessage.setText("We don't have facility to use other coin now.\nPlease choose Peet Coin now!! :)");
            sendMessage.setReplyMarkup(KeyboardFactory.getPizzaOrDrinkKeyboard());
            sender.execute(sendMessage);
        } else if ("PEET Coin".equalsIgnoreCase(message.getText())) {
            sendMessage.setText("You selected Peet Coin.\nHow much PEET will the betting pot have!");

            sendMessage.setReplyMarkup(KeyboardFactory.getPizzaToppingsKeyboard());
//            sendMessage.setText(messageText);
//            sendMessage.setParseMode("HTML"); // Set parse mode to HTML
            sendMessage.setText("Please click the following link to login: " + url);

            sender.execute(sendMessage);

            chatStates.put(chatId, AMOUNT_IN_POT);
        } else {
            sendMessage.setText("We don't sell " + message.getText() + ". Please select from the options below.");
            sendMessage.setReplyMarkup(KeyboardFactory.getPizzaOrDrinkKeyboard());
            sender.execute(sendMessage);
        }
    }
    private void amountInPot(long chatId, Message message) throws InterruptedException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        if ("$10".equalsIgnoreCase(message.getText())||"$50".equalsIgnoreCase(message.getText())||"$100".equalsIgnoreCase(message.getText())||"$500".equalsIgnoreCase(message.getText())||"$1000".equalsIgnoreCase(message.getText())) {
            sendMessage.setText("Peetbetbot has been initiated with a total pot amount of "+message.getText()+". Please wait for the confirmation.\n Select the game to play");

          //  sendMessage.setText("Peetbetbot has been initiated with a total pot amount ");
         //   sender.wait(5000);
            sendMessage.setReplyMarkup(KeyboardFactory.selectGames());
            sender.execute(sendMessage);
            chatStates.put(chatId, GAMES_TO_PLAY);
        } else if ("no".equalsIgnoreCase(message.getText())) {
            stopChat(chatId);
        } else {
            sendMessage.setText("Please select yes or no");
            sendMessage.setReplyMarkup(KeyboardFactory.getYesOrNo());
            sender.execute(sendMessage);
        }
    }

    private void selectGamesForPlaying(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
//        sendMessage.setText("Please select the game to play");
//        sender.execute(sendMessage);

        if ("Number Perdication".equalsIgnoreCase(message.getText())) {
            promptWithKeyboardForState(chatId, "You have selected Number Predication.\nHow much do you want to bet in $peet !! :)",
                    KeyboardFactory.amountToBet(), AMOUNT_TO_BET);
        }
        else {
            sendMessage.setText("We don't have facility to use other coin now.\nPlease choose Number Predication now!! :)");
            sendMessage.setReplyMarkup(KeyboardFactory.getPizzaOrDrinkKeyboard());
            sender.execute(sendMessage);
        }
    }

    private void selectAMTTobet(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        if ("$10".equalsIgnoreCase(message.getText()) || "$50".equalsIgnoreCase(message.getText()) || "$100".equalsIgnoreCase(message.getText()) || "$500".equalsIgnoreCase(message.getText()) || "$1000".equalsIgnoreCase(message.getText())) {
            sendMessage.setText("Let's play the game. Do you enjoy the game?");
            sendMessage.setReplyMarkup(KeyboardFactory.getYesOrNo());
            sender.execute(sendMessage);
            chatStates.put(chatId, USER_WON_LOST);
        }
    }

    private void userWonOrLost(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        if ("yes".equalsIgnoreCase(message.getText())) {
            promptWithKeyboardForState(chatId, "You won. Thank you!\nDo you want to play again?",
                    KeyboardFactory.getYesOrNo(), AWAITING_CONFIRMATION);
        } else if ("no".equalsIgnoreCase(message.getText())) {
            stopChat(chatId);
        } else {
            sendMessage.setText("Please select yes or no");
            sendMessage.setReplyMarkup(KeyboardFactory.getYesOrNo());
            sender.execute(sendMessage);
            chatStates.put(chatId, AWAITING_NAME);
        }
    }
   /* private void replyToPizzaToppings(long chatId, Message message) {
        if ("$10".equalsIgnoreCase(message.getText())||"$50".equalsIgnoreCase(message.getText())||"$100".equalsIgnoreCase(message.getText())||"$500".equalsIgnoreCase(message.getText())||"$1000".equalsIgnoreCase(message.getText())) {
            promptWithKeyboardForState(chatId, "You selected "+message.getText()+".\nWe will deliver it soon. Thank you!\nOrder again?",
                    KeyboardFactory.getYesOrNo(), GAMES_TO_PLAY);
        } else if ("pepperoni".equalsIgnoreCase(message.getText())) {
            promptWithKeyboardForState(chatId, "We finished the Pepperoni Pizza.\nSelect another Topping",
                    KeyboardFactory.getPizzaToppingsKeyboard(), GAMES_TO_PLAY);
        } else {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText("We don't sell " + message.getText() + " Pizza.\nSelect the toppings!");
            sendMessage.setReplyMarkup(KeyboardFactory.getPizzaToppingsKeyboard());
            sender.execute(sendMessage);
            chatStates.put(chatId, GAMES_TO_PLAY);
        }
    }
*/
    private void promptWithKeyboardForState(Long chatId, String text, ReplyKeyboard YesOrNo, UserState awaitingReorder) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(YesOrNo);
        sender.execute(sendMessage);
        chatStates.put(chatId, awaitingReorder);
    }

    private void replyToName(long chatId, Message message) {
        promptWithKeyboardForState(chatId, "Hello " + message.getText() + ". What coin address will you be betting in?",
                KeyboardFactory.getPizzaOrDrinkKeyboard(),
                UserState.FOOD_DRINK_SELECTION);
    }

    public boolean userIsActive(Long chatId) {
        return chatStates.containsKey(chatId);
    }
    // Helper method to escape characters for MarkdownV2
    private String escapeMarkdownV2(String text) {
        return text.replace("_", "\\_")
                .replace("*", "\\*")
                .replace("[", "\\[")
                .replace("]", "\\]")
                .replace("(", "\\(")
                .replace(")", "\\)")
                .replace("~", "\\~")
                .replace("`", "\\`")
                .replace(">", "\\>")
                .replace("#", "\\#")
                .replace("+", "\\+")
                .replace("-", "\\-")
                .replace("=", "\\=")
                .replace("|", "\\|")
                .replace("{", "\\{")
                .replace("}", "\\}")
                .replace(".", "\\.")
                .replace("!", "\\!");
    }
}
