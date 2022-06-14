package com.tgbot.jhelpertgbot.model.state;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatCondition {
    ASK_THE_AGE("/askAge"),
    ASK_THE_CITY("/askCity"),
    CONCLUSION("/conclusion"),
    INTRODUCTION_COMPLETED("/ready");

    private final String question;
}
