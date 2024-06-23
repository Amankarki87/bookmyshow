package bookMyShow.utils;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/** The type Mock database configuration. */
public class MockDatabaseConfiguration {

  /**
   * Data source data source.
   *
   * @return the data source
   */
  @Bean
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }
}
