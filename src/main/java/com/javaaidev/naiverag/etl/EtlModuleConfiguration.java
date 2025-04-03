package com.javaaidev.naiverag.etl;

import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EtlModuleConfiguration {

  @Bean
  public TextSplitter textSplitter() {
    return new RecursiveTextSplitter();
  }

  @Bean
  public EtlService etlService(TextSplitter textSplitter,
      VectorStore vectorStore) {
    return new EtlService(textSplitter, vectorStore);
  }
}
