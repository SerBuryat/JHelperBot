package com.tgbot.jhelpertgbot.repository;

import com.tgbot.jhelpertgbot.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByChatId(String chatId);

    boolean existsUserByChatId(String chatId);
}
