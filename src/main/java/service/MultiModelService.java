package service;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author liushixuan.6
 * @date 2025/10/21 21:31
 * @description: 多对话模型示例
 */
@Service
public class MultiModelService {

    private static final Logger logger = LoggerFactory.getLogger(MultiModelService.class);

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;

    @Resource
    private OpenAiChatModel baseChatModel;

    @Resource
    private OpenAiApi baseOpenAiApi;

    public void multiClientFlow() {
        try {
            // Derive a new OpenAiApi for Groq (Llama3)
            OpenAiApi aliApi = baseOpenAiApi.mutate()
                    .baseUrl(baseUrl)
                    .apiKey(apiKey)
                    .build();

            // Derive a new OpenAiChatModel for Groq
            OpenAiChatModel qwenFlash = baseChatModel.mutate()
                    .openAiApi(aliApi)
                    .defaultOptions(OpenAiChatOptions.builder().model("qwen-flash").temperature(0.5).build())
                    .build();

            // Derive a new OpenAiChatModel for GPT-4
            OpenAiChatModel deepseek = baseChatModel.mutate()
                    .openAiApi(aliApi)
                    .defaultOptions(OpenAiChatOptions.builder().model("deepseek-v3.2-exp").temperature(0.7).build())
                    .build();

            // Simple prompt for both models
            String prompt = "你是什么模型";

            String qwenFlashResponse = ChatClient.builder(qwenFlash).build().prompt(prompt).call().content();
            String deepseekResponse = ChatClient.builder(deepseek).build().prompt(prompt).call().content();

            logger.info("qwen-flash: {}", qwenFlashResponse);
            logger.info("deepseek: {}", deepseekResponse);
        }
        catch (Exception e) {
            logger.error("Error in multi-client flow", e);
        }
    }
}