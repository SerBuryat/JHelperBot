package com.tgbot.jhelpertgbot.service.impl;

import com.tgbot.jhelpertgbot.model.User;
import com.tgbot.jhelpertgbot.repository.UserRepository;
import com.tgbot.jhelpertgbot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findUserByChatId(String chatId) {
        return userRepository.findUserByChatId(chatId)
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public boolean isExist(String chatId) {
        return userRepository.existsUserByChatId(chatId);
    }
}
