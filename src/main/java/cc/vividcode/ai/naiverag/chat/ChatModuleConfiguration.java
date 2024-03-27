package cc.vividcode.ai.naiverag.chat;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatModuleConfiguration {

  @Bean
  public ChatService chatService(ChatClient chatClient,
      VectorStore vectorStore) {
    return new ChatService(chatClient, vectorStore);
  }
}
