package io.tguduru.main;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.tguduru.conf.HelloWorldConfiguration;
import io.tguduru.health.AppHealthCheck;
import io.tguduru.persistence.PersistenceHelper;
import io.tguduru.resource.CustomerResource;
import io.tguduru.resource.HelloWorldResource;
import io.tguduru.resource.OrderResource;
import io.tguduru.resource.ProductResource;

/**
 * Main app which starts container and REST services
 *
 * @author Thirupathi Reddy Guduru
 * @modified Jan 18, 2015
 */
public class ECommerceApplication extends Application<HelloWorldConfiguration> {
  public static void main(final String[] args) throws Exception {
    new ECommerceApplication().run(args);
  }

  @Override
  public String getName() {
    return "Simple eCommerce App";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initialize(final Bootstrap<HelloWorldConfiguration> arg0) {
    // populate date

    // add customers
    PersistenceHelper.getInstance().addCustomer("Sourav", "India", "thiru@email.com");
    PersistenceHelper.getInstance().addCustomer("Dhoni", "India", "hansi@email.com");
    PersistenceHelper.getInstance().addProduct("iPhone", 699.99);
    PersistenceHelper.getInstance().addProduct("iPad", 499.99);
    PersistenceHelper.getInstance().addProduct("iPod", 199.99);
    PersistenceHelper.getInstance().addOrder(1, 3, 5);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(final HelloWorldConfiguration configuration, final Environment environment)
      throws Exception {
    final HelloWorldResource helloWorldResource = new HelloWorldResource("world");
    final CustomerResource customerResource = new CustomerResource();
    final ProductResource productResource = new ProductResource();

    // since we have already created an instance and registered then JAX-RS runtime engine won't
    // instantiate every time, these are singletons.
    environment.jersey().register(helloWorldResource);
    environment.jersey().register(customerResource);
    environment.jersey().register(productResource);

    // since we are registering a class,its not singleton so for every request an instance will be
    // created.
    environment.jersey().register(OrderResource.class);
    // set the application root context path
    environment.jersey().setUrlPattern("/ecommerce");
    final AppHealthCheck appHealthCheck = new AppHealthCheck();
    environment.healthChecks().register("template", appHealthCheck);
  }

}
