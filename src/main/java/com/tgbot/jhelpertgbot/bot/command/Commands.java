package com.tgbot.jhelpertgbot.bot.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Commands {

  START("/start"),
  HELP("/help"),
  RESOURCE("/resource"),
  TIPS("/tips"),
  BOOKS("/books"),
  CODE("/code"),
  ALL("/all");

  private final String stringCommand;
}
