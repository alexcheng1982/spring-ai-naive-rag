package cc.vividcode.ai.naiverag.etl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentReader;

public class WebPageReader implements DocumentReader {
  private final String url;

  public WebPageReader(String url) {
    this.url = url;
  }

  @Override
  public List<Document> get() {
    try {
      var doc = Jsoup.connect(url).get();
      return List.of(new Document(doc.body().text(), Map.of(
          "url", url
      )));
    } catch (IOException e) {
      return List.of();
    }
  }
}
