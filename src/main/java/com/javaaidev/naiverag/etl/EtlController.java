package com.javaaidev.naiverag.etl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/etl")
public class EtlController {

  private final EtlService etlService;

  public EtlController(EtlService etlService) {
    this.etlService = etlService;
  }

  @PostMapping("/webpage")
  public ResponseEntity<Void> loadWebPage(
      @RequestBody WebPageLoadRequest request) {
    etlService.loadWebPage(request);
    return ResponseEntity.noContent().build();
  }
}
