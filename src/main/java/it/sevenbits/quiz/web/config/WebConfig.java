package it.sevenbits.quiz.web.config;

import it.sevenbits.quiz.web.security.JwtAuthInterceptor;
import it.sevenbits.quiz.web.security.JwtTokenService;
import it.sevenbits.quiz.web.security.UserCredentialsResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * configurer class
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final JwtTokenService jwtTokenService;

  /**
   * constructor
   * @param jwtTokenService - Jwt token service
   */
  public WebConfig(
          final JwtTokenService jwtTokenService
  ) {
    this.jwtTokenService = jwtTokenService;
  }


  @Override
  public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(new UserCredentialsResolver());
  }

  @Override
  public void addInterceptors(final InterceptorRegistry registry) {
    registry.addInterceptor(
            new JwtAuthInterceptor(jwtTokenService)
    );
  }

}
