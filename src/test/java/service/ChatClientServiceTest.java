package service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import spring.ai.example.springAIDemo.SpringAiDemoApplication;

/**
 * @author liushixuan.6
 * @date 2025/10/22 09:27
 * @description:
 */
@SpringBootTest(classes = SpringAiDemoApplication.class)
class ChatClientServiceTest {

    @Resource
    private ChatClientService chatClientService;

    @Test
    void multiClientFlow() {
        chatClientService.multiClientFlow();
    }

    @Test
    void entityTransfer() {
        chatClientService.entityTransfer();
    }

    @Test
    void streamingResponses() {
        chatClientService.streamingResponses();
    }
}