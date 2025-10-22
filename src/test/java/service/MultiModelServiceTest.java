package service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import spring.ai.example.spring_ai_demo.SpringAiDemoApplication;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liushixuan.6
 * @date 2025/10/22 09:27
 * @description:
 */
@SpringBootTest(classes = SpringAiDemoApplication.class)
class MultiModelServiceTest {

    @Resource
    private MultiModelService multiModelService;

    @Test
    void multiClientFlow() {
        multiModelService.multiClientFlow();
    }
}