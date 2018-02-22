package bot.main;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.methods.send.SendVideo;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text

        if (update.hasMessage() && update.getMessage().hasText()) {
            if (update.getMessage().getText().equals("/start")) {
                sendCustomKeyboard(update.getMessage().getChatId().toString(), 0, "Welcome to PornForAll");
            } else if (update.getMessage().getText().equals("Photo & Gif")) {
                Application.initImage();
                if (Application.finalulr.toString().endsWith("jpg")) {
                    sendCustomKeyboard(update.getMessage().getChatId().toString(), 1, Application.finalulr.toString());
                }
                if (Application.finalulr.toString().endsWith("gif")) {
                    sendCustomKeyboard(update.getMessage().getChatId().toString(), 2, Application.finalulr.toString());
                }
            } else if (update.getMessage().getText().equals("Video")) {
                sendCustomKeyboard(update.getMessage().getChatId().toString(), 3, "Coming soon!");
            } else {
                sendCustomKeyboard(update.getMessage().getChatId().toString(), 4, "Coglione credi possa rispondere alle tue cazzate?");
            }
        }

    }


    public void sendCustomKeyboard(String chatId, int code, String text) {

        // Create ReplyKeyboardMarkup object
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("Photo & Gif");
        // Add the first row to the keyboard
        keyboard.add(row);
        // Create another keyboard row
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Video");
        // Add the second row to the keyboard
        keyboard.add(row);
        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        SendMessage message;
        switch (code) {
            case 0:
                message = new SendMessage().setChatId(chatId).setText(text);
                message.setReplyMarkup(keyboardMarkup);
                try {
                    sendMessage(message);
                    break;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            case 1:
                sendImageFromUrl(Application.finalulr.toString(), chatId);
                break;
            case 2:
                try {
                    sendVideo(new SendVideo().setChatId(chatId).setVideo(Application.finalulr.toString()));
                    break;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            case 3:
                message = new SendMessage().setChatId(chatId).setText(text);
                message.setReplyMarkup(keyboardMarkup);
                try {
                    sendMessage(message);
                    break;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            case 4:
                message = new SendMessage().setChatId(chatId).setText(text);
                message.setReplyMarkup(keyboardMarkup);
                try {
                    sendMessage(message);
                    break;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

        }


    }

    @Override
    public String getBotUsername() {
        return "PornForAllBot";
    }

    @Override
    public String getBotToken() {
        return "419196658:AAGkRNNK5LXRH4TbEymJXEpExneOq9RaCbc";
    }

    public void sendImageFromUrl(String url, String chatId) {
        // Create send method
        SendPhoto sendPhotoRequest = new SendPhoto();
        // Set destination chat id
        sendPhotoRequest.setChatId(chatId);
        // Set the photo url as a simple photo
        sendPhotoRequest.setPhoto(url);
        try {
            // Execute the method
            sendPhoto(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
