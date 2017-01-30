package io.tguduru.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.tguduru.model.Customer;

/**
 * @author Guduru, Thirupathi Reddy
 * @modified 1/29/17
 */
public class RESTClient {
  public static void main(String[] args) {
    Client client = ClientBuilder.newClient();

    // GET
    Customer customer =
        client.target("http://localhost:8080/ecommerce/customers/1").request().get(Customer.class);
    System.out.println(customer);

    // POST
    Customer newCustomer = new Customer();
    newCustomer.setName("Sachin");
    newCustomer.setEmail("sachin@email.com");
    newCustomer.setAddress("India");
    Response response =
        client.target("http://localhost:8080/ecommerce/customers").request()
            .post(Entity.entity(newCustomer, MediaType.APPLICATION_JSON_TYPE));
    String newURL = response.getLocation().toString();
    System.out.println(newURL);
  }
}
