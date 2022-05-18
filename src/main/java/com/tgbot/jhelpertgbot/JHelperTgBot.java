package com.tgbot.jhelpertgbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class JHelperTgBot extends TelegramLongPollingBot {

  private final String botUsername;
  private final String botAccessToken;

  public JHelperTgBot(TelegramBotsApi telegramBotsApi,
      @Value("${bot.name}") String botUsername,
      @Value("${bot.access.token}") String botAccessToken) throws TelegramApiException {
    this.botUsername = botUsername;
    this.botAccessToken = botAccessToken;
    telegramBotsApi.registerBot(this);
  }

  @Override
  public String getBotUsername() {
    return botUsername;
  }

  @Override
  public String getBotToken() {
    return botAccessToken;
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
