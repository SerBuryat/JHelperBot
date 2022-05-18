package com.tgbot.jhelpertgbot;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class Help extends DefaultBotResponse{

  private static final Commands COMMANDS = Commands.HELP;

  @Override
  public Commands getCommand() {
    return COMMANDS;
  }

  @Override
  String getReplyText() {
    return Arrays.stream(Commands.values())
        .map(Commands::getStringCommand)
        .collect(Collectors.joining("\n"));
  }
}
