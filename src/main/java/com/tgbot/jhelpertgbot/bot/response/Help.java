package com.tgbot.jhelpertgbot.bot.response;

import com.tgbot.jhelpertgbot.bot.command.Commands;
import com.tgbot.jhelpertgbot.factory.SendMessageFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class Help implements BotResponse {

  private static final Commands COMMAND = Commands.HELP;

  @Override
  public Commands getCommand() {
    return COMMAND;
  }

  @Override
  public SendMessage reply(Update update) {
    SendMessage sendMessage = SendMessageFactory.getInstance(update);
    sendMessage.setText( Arrays.stream(Commands.values())
            .map(Commands::getStringCommand)
            .collect(Collectors.joining("\n")));
    return sendMessage;
  }
}
