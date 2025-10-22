package spring.ai.example.spring_ai_demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import reactor.core.publisher.Flux;

@SpringBootApplication
@ComponentScan(basePackages = {"spring.ai.example.spring_ai_demo", "service", "controller", "config", "example"})
public class SpringAiDemoApplication {

//    @Bean
//    public CommandLineRunner runner(ChatClient.Builder builder) {
//        return args -> {
//            Flux<String> flux = builder.build()
//                    .prompt("Tell me a joke")
//                    .stream()   // ← 开启流式
//                    .content(); // ← 返回 Flux<String>
//
//            // 逐条打印
//            flux.subscribe(System.out::print,      // 收到一段打印一段
//                    Throwable::printStackTrace,      // 异常
//                    ()    -> System.out.println("\n===done===")  // 完成
//            );
//
//            // 主线程稍等，防止 JVM 提前退出（演示用）
//            Thread.sleep(5_000);
//        };
//    }


    public static void main(String[] args) {
        SpringApplication.run(SpringAiDemoApplication.class, args);

    }

}
