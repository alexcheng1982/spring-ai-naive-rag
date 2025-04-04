package com.javaaidev.webpageqa.etl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.ai.document.DocumentTransformer;
import org.springframework.ai.document.DocumentWriter;

public class EtlService {

  private final ExecutorService executor = Executors.newThreadPerTaskExecutor(
      Thread.ofVirtual().name("etl-", 1).factory()
  );

  private final DocumentTransformer documentTransformer;
  private final DocumentWriter documentWriter;

  public EtlService(DocumentTransformer documentTransformer,
      DocumentWriter documentWriter) {
    this.documentTransformer = documentTransformer;
    this.documentWriter = documentWriter;
  }

  public void loadWebPage(WebPageLoadRequest request) {
    executor.submit(
        new EtlTask(request.url(), documentTransformer, documentWriter));
  }
}
