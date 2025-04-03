package com.javaaidev.naiverag;

import com.javaaidev.naiverag.chat.ChatModuleConfiguration;
import com.javaaidev.naiverag.etl.EtlModuleConfiguration;
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
