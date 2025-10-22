package config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liushixuan.6
 * @date 2025/10/20 20:40
 * @description: ChatClient 配置类
 */
@Configuration
public class ChatClientConfig {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;

    @Value("${spring.ai.openai.chat.options.model}")
    private String model;

    @Bean
    public OpenAiApi openAiApi() {
        return new OpenAiApi.Builder()
                .apiKey(apiKey)
                .baseUrl(baseUrl)
                .build();
    }

    @Bean
    public OpenAiChatModel openAiChatModel(OpenAiApi openAiApi) {
        OpenAiChatOptions options = new OpenAiChatOptions();
        options.setModel(model);
        
        return OpenAiChatModel.builder().defaultOptions(options).openAiApi(openAiApi).build();
    }

    @Bean
    public ChatClient openAiChatClient(OpenAiChatModel chatModel) {
        return ChatClient.create(chatModel);
    }
}