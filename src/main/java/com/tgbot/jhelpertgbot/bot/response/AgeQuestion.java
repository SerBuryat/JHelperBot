package com.tgbot.jhelpertgbot.bot.response;

import com.tgbot.jhelpertgbot.bot.command.Commands;
import com.tgbot.jhelpertgbot.factory.SendMessageFactory;
import com.tgbot.jhelpertgbot.model.User;
import com.tgbot.jhelpertgbot.model.state.ChatCondition;
import com.tgbot.jhelpertgbot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AgeQuestion implements BotResponse {

    private static final Commands COMMAND = Commands.ASK_AGE;

    private final UserService service;

    @Override
    public SendMessage reply(Update update) {
        User user = service.findUserByChatId(update.getMessage().getChatId().toString());
        user.setAge(Integer.parseInt(update.getMessage().getText()));
        user.setCondition(ChatCondition.ASK_THE_CITY);

        SendMessage sendMessage = SendMessageFactory.getInstance(update);
        sendMessage.setText("Из какого ты города?");

        return sendMessage;
    }

    @Override
    public Commands getCommand() {
        return COMMAND;
    }
}
