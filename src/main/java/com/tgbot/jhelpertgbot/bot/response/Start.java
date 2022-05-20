package com.tgbot.jhelpertgbot.bot.response;

import com.tgbot.jhelpertgbot.bot.command.Commands;
import com.tgbot.jhelpertgbot.factory.SendMessageFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class Start extends DefaultBotResponse {

  private static final Commands COMMAND = Commands.START;

  @Value("classpath:introduction.txt")
  private Resource resource;

  @Override
  public Commands getCommand() {
    return COMMAND;
  }

  @Override
  @SneakyThrows
  public SendMessage reply(Update update) {
    SendMessage sendMessage = SendMessageFactory.getInstance(update);
    String introduction = "Привет, " + update.getMessage().getFrom().getFirstName() + "!";
    Path path = Paths.get(resource.getFile().getPath());
    sendMessage.setText(introduction + "\n" + String.join("\n", Files.readAllLines(path)));

    return sendMessage;
  }

  @Override
  String getReplyText() {
    return "/start command";
  }
}
