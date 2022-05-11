package it.sevenbits.quiz.web.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.sevenbits.quiz.core.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Service to generate and parse JWT tokens.
 */
public class JsonWebTokenService implements JwtTokenService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final JwtSettings settings;
  private static final String ROLES = "roles";

  /**
   * constructor
   * @param settings - jwt settings
   */
  public JsonWebTokenService(final JwtSettings settings) {
    this.settings = settings;
  }

  @Override
  public String createToken(final User user) {
    logger.debug("Generating token for {}", user.getEmail());

    Instant now = Instant.now();

    Claims claims = Jwts.claims()
            .setIssuer(settings.getTokenIssuer())
            .setIssuedAt(Date.from(now))
            .setSubject(user.getEmail())
            .setId(user.getUserId())
            .setExpiration(Date.from(now.plus(settings.getTokenExpiredIn())));
    claims.put(ROLES, user.getRoles());

    String payload = user.getEmail();

    return Jwts.builder()
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
            .compact();
  }
  @Override
  @SuppressWarnings("unchecked")
  public UserCredentials parseToken(final String token) {
    logger.debug("Parsing token {}", token);

    Jws<Claims> claims = Jwts.parser().setSigningKey(settings.getTokenSigningKey()).parseClaimsJws(token);

    String subject = claims.getBody().getSubject();
    String id = claims.getBody().getId();
    List<String> roles = claims.getBody().get(ROLES, List.class);

    return new UserCredentialsImpl(id, subject, Collections.unmodifiableList(roles));
  }

}
