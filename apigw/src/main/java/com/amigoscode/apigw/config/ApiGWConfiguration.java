package com.amigoscode.apigw.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ali Bouali
 */
@Configuration
public class ApiGWConfiguration {

  @Bean
  public Decoder decoder(ObjectMapper objectMapper) {
    return new JacksonDecoder(objectMapper);
  }

  @Bean
  public Encoder encoder(ObjectMapper objectMapper) {
    return new JacksonEncoder(objectMapper);
  }
}
