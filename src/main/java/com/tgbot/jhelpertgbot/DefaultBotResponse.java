package com.tgbot.jhelpertgbot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class DefaultBotResponse implements BotResponse{

  @Override
  public SendMessage reply(Update update) {
    return SendMessage.builder()
        .chatId(update.getMessage().getChatId().toString())
        .text(getReplyText())
        .build();
  }

  abstract String getReplyText();
}
