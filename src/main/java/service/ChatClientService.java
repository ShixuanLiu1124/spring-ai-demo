package service;

import entity.ActorFilms;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author liushixuan.6
 * @date 2025/10/21 21:31
 * @description: ChatClient章节示例用service
 */
@Service
public class ChatClientService {

    private static final Logger logger = LoggerFactory.getLogger(ChatClientService.class);

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;

    @Resource
    private OpenAiChatModel baseChatModel;

    @Resource
    private OpenAiApi baseOpenAiApi;

    private OpenAiApi aliApi;

    private OpenAiChatModel qwenFlash;

    private OpenAiChatModel deepseek;

    private ChatClient qwenFlashChatClient;

    private ChatClient deepseekChatClient;

    @PostConstruct
    public void init() {
        aliApi = baseOpenAiApi.mutate()
                .baseUrl(baseUrl)
                .apiKey(apiKey)
                .build();

        qwenFlash = baseChatModel.mutate()
                .openAiApi(aliApi)
                .defaultOptions(OpenAiChatOptions.builder().model("qwen-flash").temperature(0.5).build())
                .build();

        deepseek = baseChatModel.mutate()
                .openAiApi(aliApi)
                .defaultOptions(OpenAiChatOptions.builder().model("deepseek-v3.2-exp").temperature(0.7).build())
                .build();

        qwenFlashChatClient = ChatClient.builder(qwenFlash).build();

        deepseekChatClient = ChatClient.builder(deepseek).build();
    }

    public void multiClientFlow() {
        try {
            String prompt = "你是什么模型";

            String qwenFlashResponse = qwenFlashChatClient.prompt(prompt).call().content();
            String deepseekResponse = deepseekChatClient.prompt(prompt).call().content();

            logger.info("qwen-flash response: {}", qwenFlashResponse);
            logger.info("deepseek response: {}", deepseekResponse);
        } catch (Exception e) {
            logger.error("Error in multi-client flow", e);
        }
    }

    public void entityTransfer() {
        Flux<String> output = qwenFlashChatClient.prompt()
                .user("Tell me a joke")
                .stream()
                .content();

        output.subscribe(System.out::println);
    }

    public void streamingResponses() {
        try {
            List<ActorFilms> actorFilmList = qwenFlashChatClient.prompt()
                    .user("Generate the filmography of 5 movies for Tom Hanks and Bill Murray.")
                    .call()
                    .entity(new ParameterizedTypeReference<>() {});

            logger.info("actorFilmList: {}", actorFilmList);
        } catch (Exception e) {
            logger.error("Error in streaming responses", e);
        }
    }
}