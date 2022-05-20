package com.tgbot.jhelpertgbot.factory;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SendMessageFactory {

    public static SendMessage getInstance(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setReplyToMessageId(update.getMessage().getMessageId());

        return sendMessage;
    }
}
