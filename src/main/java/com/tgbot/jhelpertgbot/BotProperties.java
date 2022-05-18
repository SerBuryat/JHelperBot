package com.tgbot.jhelpertgbot;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "bot")
public class BotProperties {

  private String name;
  private String token;
}
