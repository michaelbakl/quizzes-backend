package it.sevenbits.quiz.web.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * migrations for database config
 */
@Configuration
public class DatabaseMigrationsConfig {

  private final DataSource dataSource;

  /**
   * constructor
   * @param dataSource - data source
   */
  public DatabaseMigrationsConfig(final DataSource dataSource) {
    this.dataSource = dataSource;
  }

  /**
   * flyway creator
   * @return Flyway
   */
  @Bean
  public Flyway creativesManagementFlyway() {
    Flyway flyway = Flyway
            .configure()
            .dataSource(this.dataSource)
            .table("flyway_schema")
            .baselineOnMigrate(true)
            .locations("db/migrations")
            .load();
    try {
      flyway.migrate();
    } catch (FlywayException e) {
      flyway.repair();
      flyway.migrate();
    }
    return flyway;
  }

}
