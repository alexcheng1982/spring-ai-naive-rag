package com.javaaidev.naiverag.etl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.DocumentTransformer;
import org.springframework.ai.document.DocumentWriter;

public class EtlTask implements Runnable {

  private final String webPageUrl;
  private final DocumentTransformer documentTransformer;
  private final DocumentWriter documentWriter;

  private static final Logger LOGGER = LoggerFactory.getLogger(EtlTask.class);

  public EtlTask(String webPageUrl, DocumentTransformer documentTransformer,
      DocumentWriter documentWriter) {
    this.webPageUrl = webPageUrl;
    this.documentTransformer = documentTransformer;
    this.documentWriter = documentWriter;
  }

  @Override
  public void run() {
    LOGGER.info("Load web page: {}", webPageUrl);
    var reader = new WebPageReader(webPageUrl);
    var docs = documentTransformer.apply(reader.get());
    LOGGER.info("{} docs to store", docs.size());
    var index = 0;
    for (var doc : docs) {
      LOGGER.info("Save doc #{}", index + 1);
      documentWriter.accept(List.of(doc));
      index++;
    }
    LOGGER.info("Imported documents");
  }
}
