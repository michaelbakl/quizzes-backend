package it.sevenbits.quiz.web.config;

import it.sevenbits.quiz.core.security.BCryptPasswordEncoder;
import it.sevenbits.quiz.core.security.PasswordEncoder;
import it.sevenbits.quiz.web.security.JsonWebTokenService;
import it.sevenbits.quiz.web.security.JwtSettings;
import it.sevenbits.quiz.web.security.JwtTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * web security config
 */
@Configuration
public class SecurityConfig {

  /**
   * password encoder config
   * @return PasswordEncoder class
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * jsw token service config
   * @param settings - jwt settings
   * @return JwtTokenService class
   */
  @Bean
  public JwtTokenService jwtTokenService(final JwtSettings settings) {
    return new JsonWebTokenService(settings);
  }

}
