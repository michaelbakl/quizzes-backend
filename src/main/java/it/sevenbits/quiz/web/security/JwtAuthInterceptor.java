package it.sevenbits.quiz.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Spring interceptor for JWT based authentication and authorization
 */
public class JwtAuthInterceptor implements HandlerInterceptor {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * define of user credentials
   */
  @SuppressWarnings("checkstyle:StaticVariableName")
  public static String USER_CREDENTIALS = "userCredentialsAttr";

  private final JwtTokenService jwtService;

  /**
   * constructor
   * @param jwtService - jwt service
   */
  public JwtAuthInterceptor(
          final JwtTokenService jwtService
  ) {
    this.jwtService = jwtService;
  }

  @Override
  public boolean preHandle(@NonNull final HttpServletRequest request,
                           @NonNull final HttpServletResponse response,
                           @NonNull final Object handler) {
    if (handler instanceof HandlerMethod) {
      HandlerMethod handlerMethod = (HandlerMethod) handler;
      Method method = handlerMethod.getMethod();
      return checkAuthorization(method, request, response);
    }
    return true;
  }

  private UserCredentials getUserCredentials(final HttpServletRequest request) {
    try {
      String header = request.getHeader(HttpHeaders.AUTHORIZATION);
      if (header == null) {
        return null;
      }
      final int seven = 7;
      String token = header.substring(seven);
      UserCredentials credentials = jwtService.parseToken(token);
      logger.debug("Found credentials in Authorization header: {}", credentials.getUsername());
      request.setAttribute(USER_CREDENTIALS, credentials);
      return credentials;
    } catch (Exception e) {
      return null;
    }
  }

  private boolean checkAuthorization(
          final Method method,
          final HttpServletRequest request,
          final HttpServletResponse response
  ) {
    try {
      UserCredentials userCredentials = getUserCredentials(request);

      if (method.isAnnotationPresent(AuthRoleRequired.class)) {
        if (userCredentials == null) {
          response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
          return false;
        }
        AuthRoleRequired annotation = method.getAnnotation(AuthRoleRequired.class);
        String requiredRole = annotation.value();
        Set<String> userRoles = new HashSet<>();
        if (userCredentials.getRoles() != null) {
          userRoles.addAll(userCredentials.getRoles());
        }
        boolean isAuthorized = userRoles.contains(requiredRole);
        if (!isAuthorized) {
          response.sendError(HttpServletResponse.SC_FORBIDDEN, "Not enough permissions");
          return false;
        }
      }

      return true;
    } catch (Exception e) {
      return false;
    }
  }

}

