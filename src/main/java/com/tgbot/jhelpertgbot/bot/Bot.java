package com.tgbot.jhelpertgbot.bot;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import com.tgbot.jhelpertgbot.bot.response.BotResponse;
import com.tgbot.jhelpertgbot.config.BotProperties;
import com.tgbot.jhelpertgbot.model.User;
import com.tgbot.jhelpertgbot.model.state.ChatCondition;
import com.tgbot.jhelpertgbot.service.UserService;
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
  private final BotProperties botProperties;
  private final List<BotResponse> responseList;
  private final UserService service;

  private Map<String,BotResponse> commandResponseMap;

  @PostConstruct
  @SneakyThrows
  private void init() {
    telegramBotsApi.registerBot(this);
    commandResponseMap = responseList.stream().collect(Collectors
        .toMap(response -> response.getCommand().getStringCommand(), response -> response));
  }

  @Override
  public String getBotUsername() {
    return botProperties.getName();
  }

  @Override
  public String getBotToken() {
    return botProperties.getToken();
  }

  @SneakyThrows
  @Override
  public void onUpdateReceived(Update update) {
    SendMessage sendMessage;
    BotResponse response;

    if (!service.isExist(update.getMessage().getChatId().toString())) {
      response = commandResponseMap.get("/start");
    } else {
      User user = service.findUserByChatId(update.getMessage().getChatId().toString());

      if (user.getCondition().equals(ChatCondition.INTRODUCTION_COMPLETED)) {
        response = commandResponseMap.get(update.getMessage().getText());
      } else {
        response = commandResponseMap.get(user.getCondition().getQuestion());
      }
    }

    sendMessage = getSendMessage(response, update);

    sendApiMethod(sendMessage);
  }

  private SendMessage getSendMessage(BotResponse response, Update update) {
    SendMessage sendMessage;

    if (response == null) {
      sendMessage = SendMessage.builder()
              .chatId(update.getMessage().getChatId().toString())
              .text("No command found, use /help to see command list!")
              .build();
    } else {
      sendMessage = response.reply(update);
    }

    return sendMessage;
  }
}
