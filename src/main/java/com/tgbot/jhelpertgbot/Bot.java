package com.tgbot.jhelpertgbot;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

  private final TelegramBotsApi telegramBotsApi;
  private final BotProperties configData;

  @PostConstruct
  @SneakyThrows
  private void init() {
    telegramBotsApi.registerBot(this);
  }

  @Override
  public String getBotUsername() {
    return configData.getName();
  }

  @Override
  public String getBotToken() {
    return configData.getToken();
  }

  @Override
  public void onUpdateReceived(Update update) {
    sendMessage(update);
  }

  @SneakyThrows
  private void sendMessage(Update update) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(update.getMessage().getChatId().toString());
    sendMessage.setText("Bot in progress...");
    sendApiMethod(sendMessage);
  }
}
