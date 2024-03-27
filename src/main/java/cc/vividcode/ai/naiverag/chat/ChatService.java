package cc.vividcode.ai.naiverag.chat;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

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
    var documents = findDocuments(request.message());
    var prompt = new PromptTemplate(promptRag).create(
        Map.of("query", request.message(),
            "documents", documents)
    );
    return chatClient.call(prompt).getResult().getOutput().getContent();
  }

  private String findDocuments(String query) {
    var docs = vectorStore.similaritySearch(
        SearchRequest.query(query)
            .withSimilarityThresholdAll()
            .withTopK(3));
    return docs.stream().map(Document::getContent)
        .collect(Collectors.joining("\r\n\r\n"));
  }
}
