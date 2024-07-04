package com.example.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot {

}
/*
@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            Message incomeMessage = update.getMessage();
            Long chatId = incomeMessage.getChatId();
            if (incomeMessage.hasText()) {
                String text = incomeMessage.getText();
                if (text.equals("hi")) {
                    sendMessage(chatId, "kesa hai Pankaj Curry");
                } else {
                    sendMessage(chatId, "Unknown command!");
                }
            } else {
                sendMessage(chatId, "Incorrect data");
            }
        }
    }
private void sendMessage (Long chatId, String messageToSend) {
    SendMessage message = new SendMessage();
    message.setChatId(String.valueOf(chatId));
    message.setText(messageToSend);

    try {
        execute(message);
    } catch (TelegramApiException e) {
        e.printStackTrace();
    }

}
    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
    }
}



*/
