package com.monaschinas.readr.comment.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("profileMappingConfiguration")
public class MappingConfiguration {
  @Bean
  public  CommentMapper commentMapper() {return new CommentMapper();}
}
