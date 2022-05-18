package com.tgbot.jhelpertgbot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Commands {

  START("/start"),HELP("/help");

  private final String stringCommand;
}
