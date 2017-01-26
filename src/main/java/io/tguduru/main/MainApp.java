package io.tguduru.main;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import io.tguduru.rest.health.AppHealthCheck;
import io.tguduru.conf.HelloWorldConfiguration;
import io.tguduru.rest.resource.HelloWorldResource;

/**
 * Main app which starts container and REST services
 * @author Thirupathi Reddy Guduru
 * @param <T>
 * @modified Jan 18, 2015
 */
public class MainApp extends Application<HelloWorldConfiguration> {
    public static void main(final String[] args) throws Exception {
        new MainApp().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final Bootstrap<HelloWorldConfiguration> arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run(final HelloWorldConfiguration configuration, final Environment environment) throws Exception {
        final HelloWorldResource helloWorldResource = new HelloWorldResource(configuration.getTemplate(),
                configuration.getDefaultName());
        environment.jersey().register(helloWorldResource);
        final AppHealthCheck appHealthCheck = new AppHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", appHealthCheck);
    }

}
