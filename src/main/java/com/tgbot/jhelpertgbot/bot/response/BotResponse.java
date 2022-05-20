package com.tgbot.jhelpertgbot.bot.response;

import com.tgbot.jhelpertgbot.bot.command.Commands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotResponse {

  SendMessage reply(Update update);

  Commands getCommand();
}
