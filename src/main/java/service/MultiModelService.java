package service;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;

/**
 * @author liushixuan.6
 * @date 2025/10/21 21:31
 * @description: 多对话模型示例
 */
@Service
public class MultiModelService {

    private static final Logger logger = LoggerFactory.getLogger(MultiModelService.class);

    @Resource
    private OpenAiChatModel baseChatModel;

    @Resource
    private OpenAiApi baseOpenAiApi;

    public void multiClientFlow() {
        try {
            // Derive a new OpenAiApi for Groq (Llama3)
            OpenAiApi groqApi = baseOpenAiApi.mutate()
                    .baseUrl("https://api.groq.com/openai")
                    .apiKey(System.getenv("GROQ_API_KEY"))
                    .build();

            // Derive a new OpenAiApi for OpenAI GPT-4
            OpenAiApi gpt4Api = baseOpenAiApi.mutate()
                    .baseUrl("https://api.openai.com")
                    .apiKey(System.getenv("OPENAI_API_KEY"))
                    .build();

            // Derive a new OpenAiChatModel for Groq
            OpenAiChatModel groqModel = baseChatModel.mutate()
                    .openAiApi(groqApi)
                    .defaultOptions(OpenAiChatOptions.builder().model("llama3-70b-8192").temperature(0.5).build())
                    .build();

            // Derive a new OpenAiChatModel for GPT-4
            OpenAiChatModel gpt4Model = baseChatModel.mutate()
                    .openAiApi(gpt4Api)
                    .defaultOptions(OpenAiChatOptions.builder().model("gpt-4").temperature(0.7).build())
                    .build();

            // Simple prompt for both models
            String prompt = "What is the capital of France?";

            String groqResponse = ChatClient.builder(groqModel).build().prompt(prompt).call().content();
            String gpt4Response = ChatClient.builder(gpt4Model).build().prompt(prompt).call().content();

            logger.info("Groq (Llama3) response: {}", groqResponse);
            logger.info("OpenAI GPT-4 response: {}", gpt4Response);
        }
        catch (Exception e) {
            logger.error("Error in multi-client flow", e);
        }
    }
}