package com.tgbot.jhelpertgbot.bot.response;

import com.tgbot.jhelpertgbot.bot.command.Commands;
import com.tgbot.jhelpertgbot.factory.SendMessageFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Start extends DefaultBotResponse {

  private static final Commands COMMANDS = Commands.START;

  @Override
  public Commands getCommand() {
    return COMMANDS;
  }

  @Override
  @SneakyThrows
  public SendMessage reply(Update update) {
    SendMessage sendMessage = SendMessageFactory.getInstance(update);

    String introduction = "Привет, " + update.getMessage().getFrom().getFirstName() + "!";
    Path path = Paths.get("src/main/resources/introduction.txt");
    sendMessage.setText(introduction + "\n" + String.join("\n", Files.readAllLines(path)));

    return sendMessage;
  }

  @Override
  String getReplyText() {
    return "/start command";
  }
}
