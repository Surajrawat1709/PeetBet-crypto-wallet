package com.example.bot;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public class KeyboardFactory {

    public static ReplyKeyboard getPizzaToppingsKeyboard() {
        KeyboardRow row = new KeyboardRow();
        row.add("$10");
        row.add("$50");
        row.add("$100");
        row.add("$500");
        row.add("$1000");
        return new ReplyKeyboardMarkup(List.of(row));
    }

    public static ReplyKeyboard getPizzaOrDrinkKeyboard(){
        KeyboardRow row = new KeyboardRow();
        row.add("PEET Coin");
        row.add("Other Coin");
        return new ReplyKeyboardMarkup(List.of(row));
    }

    public static ReplyKeyboard selectGames(){
        KeyboardRow row = new KeyboardRow();
        row.add("Number Perdication");
        row.add("Other games");
        return new ReplyKeyboardMarkup(List.of(row));
    }

    public static ReplyKeyboard amountToBet(){
        KeyboardRow row = new KeyboardRow();
        row.add("$10");
        row.add("$50");
        row.add("$100");
        row.add("$500");
        row.add("$1000");
        return new ReplyKeyboardMarkup(List.of(row));
    }

    public static ReplyKeyboard getYesOrNo() {
        KeyboardRow row = new KeyboardRow();
        row.add("Yes");
        row.add("No");
        return new ReplyKeyboardMarkup(List.of(row));
    }
}