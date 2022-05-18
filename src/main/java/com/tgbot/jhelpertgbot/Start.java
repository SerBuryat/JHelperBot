package com.tgbot.jhelpertgbot;

import org.springframework.stereotype.Component;

@Component
public class Start extends DefaultBotResponse{

  private static final Commands COMMANDS = Commands.START;

  @Override
  public Commands getCommand() {
    return COMMANDS;
  }

  @Override
  String getReplyText() {
    return "/start command";
  }
}
