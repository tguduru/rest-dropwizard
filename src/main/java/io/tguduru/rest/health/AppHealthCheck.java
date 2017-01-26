package io.tguduru.rest.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * Description.
 * @author Thirupathi Reddy Guduru
 * @modified Jan 18, 2015
 */
public class AppHealthCheck extends HealthCheck {
    private final String template;

    /**
     * @param template
     */
    public AppHealthCheck(final String template) {
        this.template = template;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Result check() throws Exception {
        final String saying = String.format(template, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }

}
