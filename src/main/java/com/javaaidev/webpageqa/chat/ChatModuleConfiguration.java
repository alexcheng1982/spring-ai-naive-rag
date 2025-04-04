package com.javaaidev.webpageqa.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatModuleConfiguration {

  @Bean
  public ChatClient chatClient(ChatClient.Builder builder,
      VectorStore vectorStore) {
    return builder.defaultAdvisors(new QuestionAnswerAdvisor(vectorStore)).build();
  }
}
