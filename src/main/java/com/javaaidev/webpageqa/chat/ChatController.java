package com.javaaidev.webpageqa.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

  private final ChatClient chatClient;

  public ChatController(ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  @PostMapping("/chat")
  public ChatResponse chat(@RequestBody ChatRequest request) {
    return new ChatResponse(chatClient.prompt().user(request.input()).call().content());
  }
}
