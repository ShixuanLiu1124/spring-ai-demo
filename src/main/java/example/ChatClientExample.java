//package example;
//
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Scanner;
//
///**
// * @author liushixuan.6
// * @date 2025/10/21 21:33
// * @description: ChatClient 示例
// */
//@Configuration
//public class ChatClientExample {
//
//    @Bean
//    CommandLineRunner cli(ChatClient openAiChatClient) {
//        return args -> {
//            var scanner = new Scanner(System.in);
//
//            System.out.println("\nUsing OpenAI model");
//
//            // Use the chat client
//            System.out.print("\nEnter your question: ");
//            String input = scanner.nextLine();
//            String response = openAiChatClient.prompt(input).call().content();
//            System.out.println("ASSISTANT: " + response);
//
//            scanner.close();
//        };
//    }
//}