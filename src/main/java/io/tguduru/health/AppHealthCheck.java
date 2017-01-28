package io.tguduru.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * Description.
 * 
 * @author Thirupathi Reddy Guduru
 * @modified Jan 18, 2015
 */
public class AppHealthCheck extends HealthCheck {

  /**
   */
  public AppHealthCheck() {}

  /**
   * {@inheritDoc}
   */
  @Override
  protected Result check() throws Exception {
    return Result.healthy();
  }

}
