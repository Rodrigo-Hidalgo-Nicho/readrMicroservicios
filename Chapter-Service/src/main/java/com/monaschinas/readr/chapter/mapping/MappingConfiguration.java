package com.monaschinas.readr.chapter.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("profileMappingConfiguration")
public class MappingConfiguration {
  @Bean
  public  ChapterMapper chapterMapper() {return new ChapterMapper();}
}
