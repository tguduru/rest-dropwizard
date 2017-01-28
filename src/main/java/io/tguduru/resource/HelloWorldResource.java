package io.tguduru.resource;

import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.codahale.metrics.annotation.Timed;

import io.tguduru.response.HelloWorldResponse;

/**
 * A REST resource implementation for hello world
 *
 * @author Thirupathi Reddy Guduru
 * @modified Jan 18, 2015
 */
@Path("/hello")
public class HelloWorldResource {
  private final String defaultName;
  private final AtomicLong counter;

  /**
   * @param defaultName
   */
  public HelloWorldResource(final String defaultName) {
    this.defaultName = defaultName;
    this.counter = new AtomicLong();
  }

  @GET
  @Timed
  @Produces(MediaType.APPLICATION_JSON)
  public HelloWorldResponse helloWorld(@QueryParam("name") final Optional<String> name) {
    final String val =
        name.isPresent() ? "Hello, " + name.get() + " !!! " : "Hello, " + defaultName + " !!!";
    return new HelloWorldResponse(val, this.counter.incrementAndGet());
  }

  @HEAD
  @Timed
  public void getHead() {
    System.out.println("Executing HEAD method");
  }

  @OPTIONS
  @Produces(MediaType.APPLICATION_JSON)
  public String getOptions() {
    System.out.println("Executing OPTIONS method");
    return "OPTIONS";
  }

}
