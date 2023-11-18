package com.monaschinas.readr.user.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("profileMappingConfiguration")
public class MappingConfiguration {
  @Bean
  public RoleMapper roleMapper() {
    return new RoleMapper();
  }

  @Bean
  public ProfileMapper profileMapper() {
    return new ProfileMapper();
  }

  @Bean
  public FavoriteMapper favoriteMapper(){ return new FavoriteMapper(); }

  @Bean
  public UserMapper userMapper(){ return new UserMapper(); }
}
