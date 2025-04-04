package com.javaaidev.webpageqa.etl;

import java.net.Socket;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentReader;

public class WebPageReader implements DocumentReader {

  private static final Logger LOGGER = LoggerFactory.getLogger(WebPageReader.class);

  private final String url;

  public WebPageReader(String url) {
    this.url = url;
  }

  @Override
  public List<Document> get() {
    try {
      var trustManager = createTrustManager();
      var sslContext = SSLContext.getInstance("TLS");
      sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
      var doc = Jsoup.connect(url).sslSocketFactory(sslContext.getSocketFactory()).get();
      return List.of(new Document(doc.body().text(), Map.of(
          "url", url
      )));
    } catch (Exception e) {
      LOGGER.error("Failed to load web page", e);
      return List.of();
    }
  }

  private X509ExtendedTrustManager createTrustManager() {
    return new X509ExtendedTrustManager() {

      @Override
      public void checkClientTrusted(X509Certificate[] chain, String authType)
          throws CertificateException {

      }

      @Override
      public void checkServerTrusted(X509Certificate[] chain, String authType)
          throws CertificateException {

      }

      @Override
      public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
      }

      @Override
      public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket)
          throws CertificateException {

      }

      @Override
      public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket)
          throws CertificateException {

      }

      @Override
      public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine)
          throws CertificateException {

      }

      @Override
      public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine)
          throws CertificateException {

      }
    };
  }

}
