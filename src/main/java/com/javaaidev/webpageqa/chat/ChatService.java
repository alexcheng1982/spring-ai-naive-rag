package com.javaaidev.webpageqa.chat;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;

public class ChatService {

  @Value("classpath:/prompts/rag.st")
  private Resource promptRag;

  private final ChatClient chatClient;
  private final VectorStore vectorStore;

  public ChatService(ChatClient chatClient, VectorStore vectorStore) {
    this.chatClient = chatClient;
    this.vectorStore = vectorStore;
  }

  public String chat(ChatRequest request) {
    var documents = findDocuments(request.input());
    var prompt = new PromptTemplate(promptRag).create(
        Map.of("query", request.input(),
            "documents", documents)
    );
    return chatClient.prompt(prompt).call().content();
  }

  private String findDocuments(String query) {
    var docs = vectorStore.similaritySearch(
        SearchRequest.builder().query(query)
            .similarityThresholdAll()
            .topK(3).build());
    if (CollectionUtils.isEmpty(docs)) {
      return "";
    }
    return docs.stream().map(Document::getText)
        .collect(Collectors.joining("\r\n\r\n"));
  }
}
