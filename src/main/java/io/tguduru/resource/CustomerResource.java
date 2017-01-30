package io.tguduru.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.URI;
import java.util.Collection;
import java.util.Map;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Maps;

import io.tguduru.model.Customer;
import io.tguduru.model.Order;
import io.tguduru.model.Product;
import io.tguduru.persistence.PersistenceHelper;
import io.tguduru.response.CustomerResponse;
import io.tguduru.response.OrderDetails;

/**
 * @author Guduru, Thirupathi Reddy
 * @modified 1/26/17
 */
@Path("/customers")
public class CustomerResource {

  @GET
  @Path("/{id}")
  @Timed
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCustomer(@PathParam("id") final Long id) {
    final Customer customer = PersistenceHelper.getInstance().getCustomers().get(id);
    if (customer == null)
      return Response.serverError().entity("CustomerResponse not found for id: " + id).build();
    return Response.ok().entity(customer).build();
  }

  @GET
  @Timed
  @Produces(MediaType.APPLICATION_JSON)
  public Collection<Customer> getCustomers() {
    return PersistenceHelper.getInstance().getCustomers().values();
  }

  @GET
  @Timed
  @Path("/{id}/orders")
  @Produces(MediaType.APPLICATION_JSON)
  public CustomerResponse getOrders(@PathParam("id") long customerId) {
    Map<Long, Order> orders = PersistenceHelper.getInstance().getCustomerOrders().get(customerId);
    Map<Long, OrderDetails> orderDetailsMap = Maps.newHashMap();

    for (Map.Entry<Long, Order> orderEntry : orders.entrySet()) {
      Order order = orderEntry.getValue();
      Product product = PersistenceHelper.getInstance().getProducts().get(order.getProductId());
      OrderDetails orderDetails = new OrderDetails();
      orderDetails.setOrderId(order.getId());
      orderDetails.setProduct(product);
      orderDetails.setQuantity(order.getQuantity());
      orderDetails.setTotalPrice(order.getTotalPrice());
      orderDetailsMap.put(order.getId(), orderDetails);
    }
    CustomerResponse response = new CustomerResponse();
    response.setCustomer(PersistenceHelper.getInstance().getCustomers().get(customerId));
    response.setOrderMap(orderDetailsMap);
    return response;
  }

  @GET
  @Timed
  @Path("/{customerId}/orders/{orderId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Order getOrders(@PathParam("customerId") long customerId,
      @PathParam("orderId") long orderId) {
    return PersistenceHelper.getInstance().getCustomerOrders().get(customerId).get(orderId);
  }

  @POST
  @Timed
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createCustomer(Customer customer) {
    long id =
        PersistenceHelper.getInstance().addCustomer(customer.getName(), customer.getAddress(),
            customer.getEmail());
    return Response.created(URI.create("/customers/" + id)).build();
  }
}
