package cc.vividcode.ai.naiverag;

import cc.vividcode.ai.naiverag.chat.ChatModuleConfiguration;
import cc.vividcode.ai.naiverag.etl.EtlModuleConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({EtlModuleConfiguration.class, ChatModuleConfiguration.class})
public class NaiveRagApplication {

  public static void main(String[] args) {
    SpringApplication.run(NaiveRagApplication.class, args);
  }

}
