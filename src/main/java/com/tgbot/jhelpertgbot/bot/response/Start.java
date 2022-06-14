package com.tgbot.jhelpertgbot.bot.response;

import com.tgbot.jhelpertgbot.bot.command.Commands;
import com.tgbot.jhelpertgbot.factory.SendMessageFactory;
import com.tgbot.jhelpertgbot.model.User;
import com.tgbot.jhelpertgbot.model.state.ChatCondition;
import com.tgbot.jhelpertgbot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Start implements BotResponse {

  private static final Commands COMMAND = Commands.START;

  private final UserService service;

  @Value("classpath:introduction.txt")
  private Resource resourceFile;

  @Override
  public Commands getCommand() {
    return COMMAND;
  }

  @Override
  @SneakyThrows
  public SendMessage reply(Update update) {
    SendMessage sendMessage = SendMessageFactory.getInstance(update);

    registerUser(update);

    String introduction = "Привет, " + update.getMessage().getFrom().getFirstName() + "!";

    sendMessage.setText(introduction + "\n" + String.join("\n",
            Files.readAllLines(resourceFile.getFile().toPath())) + "\n"
                    + "Сколько тебе лет, дружбанчик?");

    return sendMessage;
  }

  private void registerUser(Update update) {
    if (!service.isExist(update.getMessage().getChatId().toString())) {
      service.saveUser(
              User.builder()
                      .chatId(update.getMessage().getChatId().toString())
                      .name(update.getMessage().getFrom().getFirstName())
                      .condition(ChatCondition.ASK_THE_AGE)
                      .build()
      );
    }
  }
}
