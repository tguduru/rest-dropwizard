package io.tguduru.rest.resource;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import io.tguduru.rest.model.HelloWorldResponse;

import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A REST resource implementation for hello world
 *
 * @author Thirupathi Reddy Guduru
 * @modified Jan 18, 2015
 */
@Path("/hello")
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    /**
     * @param template
     * @param defaultName
     */
    public HelloWorldResource(final String template, final String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        counter = new AtomicLong();
    }

    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public HelloWorldResponse helloWorld(@QueryParam("name") final Optional<String> name) {
        final String val = String.format(template, name.or(defaultName));
        return new HelloWorldResponse(val, counter.incrementAndGet());
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
