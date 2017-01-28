package io.tguduru.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.Collection;

import com.codahale.metrics.annotation.Timed;

import io.tguduru.model.Product;
import io.tguduru.persistence.PersistenceHelper;

/**
 * Product Resource
 * 
 * @author Guduru, Thirupathi Reddy
 * @modified 1/27/17
 */
@Path("/products")
public class ProductResource {


  @GET
  @Timed
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Product getProduct(@PathParam("id") long productId) {
    return PersistenceHelper.getInstance().getProducts().get(productId);
  }

  @GET
  @Timed
  @Produces(MediaType.APPLICATION_JSON)
  public Collection<Product> getProducts() {
    return PersistenceHelper.getInstance().getProducts().values();
  }

}
