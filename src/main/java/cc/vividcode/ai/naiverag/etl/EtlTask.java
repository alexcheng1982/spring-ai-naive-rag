package cc.vividcode.ai.naiverag.etl;

import org.springframework.ai.document.DocumentTransformer;
import org.springframework.ai.document.DocumentWriter;

public class EtlTask implements Runnable {

  private final String webPageUrl;
  private final DocumentTransformer documentTransformer;
  private final DocumentWriter documentWriter;

  public EtlTask(String webPageUrl, DocumentTransformer documentTransformer,
      DocumentWriter documentWriter) {
    this.webPageUrl = webPageUrl;
    this.documentTransformer = documentTransformer;
    this.documentWriter = documentWriter;
  }

  @Override
  public void run() {
    var reader = new WebPageReader(webPageUrl);
    documentWriter.accept(documentTransformer.apply(reader.get()));
  }
}
