package com.tgbot.jhelpertgbot.bot.response;

import com.tgbot.jhelpertgbot.bot.response.BotResponse;
import com.tgbot.jhelpertgbot.factory.SendMessageFactory;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class DefaultBotResponse implements BotResponse {

  @Override
  public SendMessage reply(Update update) {
    SendMessage sendMessage = SendMessageFactory.getInstance(update);
    sendMessage.setText(getReplyText());

    return sendMessage;
  }

  abstract String getReplyText();
}
