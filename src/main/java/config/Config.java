package config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liushixuan.6
 * @date 2025/10/23 20:33
 * @description:
 */
@Configuration
public class Config {
    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.defaultSystem("你总是用鲁迅的风格回答").build();
    }
}
