package controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liushixuan.6
 * @date 2025/10/20 20:19
 * @description: Spring 对话Controller
 */
@RestController
class MyController {

    private final ChatClient chatClient;

    public MyController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/ai")
    ChatResponse generation(String userInput) {

        ChatResponse chatResponse = chatClient.prompt()
                .user(userInput)
                .call()
                .chatResponse();

        return chatResponse;
    }
}