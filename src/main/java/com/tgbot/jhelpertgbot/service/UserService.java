package com.tgbot.jhelpertgbot.service;

import com.tgbot.jhelpertgbot.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findUserByChatId(String chatId);

    void saveUser(User user);

    void deleteUser(User user);

    boolean isExist(String chatId);
}
