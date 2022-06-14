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

@Component
@RequiredArgsConstructor
public class RegistrationConclusion implements BotResponse {

    private static final Commands COMMAND = Commands.FINAL_NOTIFICATION;

    private final UserService service;

    @Override
    public SendMessage reply(Update update) {
        User user = service.findUserByChatId(update.getMessage().getChatId().toString());
        user.setCondition(ChatCondition.INTRODUCTION_COMPLETED);

        SendMessage sendMessage = SendMessageFactory.getInstance(update);
        sendMessage.setText("Регистрация завершена, спасибо за уделенное время!" + "\n"
        + "Воспользуйся коммандой /help для просмотра списка возможностей бота");

        return sendMessage;
    }

    @Override
    public Commands getCommand() {
        return COMMAND;
    }
}
