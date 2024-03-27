package cc.vividcode.ai.naiverag.etl;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "redis")
public record RedisConfig(String url, String indexName) {

}
