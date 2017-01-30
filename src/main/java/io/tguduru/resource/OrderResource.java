package io.tguduru.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

import com.codahale.metrics.annotation.Timed;

import io.tguduru.model.Order;
import io.tguduru.persistence.PersistenceHelper;

/**
 * Order Resource
 * 
 * @author Guduru, Thirupathi Reddy
 * @modified 1/29/17
 */
@Path("orders")
public class OrderResource {
  private static AtomicLong instanceCount = new AtomicLong();
  long count;

  public OrderResource() {
    this.count = instanceCount.incrementAndGet();
  }

  @GET
  @Timed
  @Produces(MediaType.APPLICATION_JSON)
  public Response getOrders() {
    Collection<Order> orders = PersistenceHelper.getInstance().getOrders().values();
    System.out.println(count);
    return Response.ok().entity(orders).build();
  }
}
