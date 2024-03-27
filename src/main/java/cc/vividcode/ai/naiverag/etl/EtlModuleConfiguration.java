package cc.vividcode.ai.naiverag.etl;

import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.vectorstore.RedisVectorStore;
import org.springframework.ai.vectorstore.RedisVectorStore.MetadataField;
import org.springframework.ai.vectorstore.RedisVectorStore.RedisVectorStoreConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(RedisConfig.class)
@Configuration
public class EtlModuleConfiguration {

  @Bean
  public RedisVectorStore redisVectorStore(
      RedisConfig redisConfig,
      EmbeddingClient embeddingClient) {
    var config = RedisVectorStoreConfig.builder()
        .withURI(redisConfig.url())
        .withIndexName(redisConfig.indexName())
        .withMetadataFields(
            MetadataField.tag("url")
        )
        .build();
    return new RedisVectorStore(config, embeddingClient);
  }

  @Bean
  public TextSplitter textSplitter() {
    return new RecursiveTextSplitter();
  }

  @Bean
  public EtlService etlService(TextSplitter textSplitter,
      RedisVectorStore redisVectorStore) {
    return new EtlService(textSplitter, redisVectorStore);
  }
}
