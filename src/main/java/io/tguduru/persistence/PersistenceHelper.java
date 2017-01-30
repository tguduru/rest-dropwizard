package io.tguduru.persistence;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.collect.Maps;

import io.tguduru.model.Customer;
import io.tguduru.model.Order;
import io.tguduru.model.Product;

/**
 * Entity helper
 *
 * @author Guduru, Thirupathi Reddy
 * @modified 1/26/17
 */
public class PersistenceHelper {
  private static final PersistenceHelper ourInstance = new PersistenceHelper();
  private final Map<Long, Customer> customers;
  private final Map<Long, Product> products;
  private final Map<Long, Order> orders;
  private final Map<Long, Map<Long, Order>> customerOrders;
  private final AtomicLong atomicLong;

  private PersistenceHelper() {
    customers = Maps.newConcurrentMap();
    orders = Maps.newConcurrentMap();
    products = Maps.newConcurrentMap();
    customerOrders = Maps.newConcurrentMap();
    this.atomicLong = new AtomicLong();
  }

  public static PersistenceHelper getInstance() {
    return ourInstance;
  }

  public Map<Long, Customer> getCustomers() {
    return this.customers;
  }

  public Map<Long, Product> getProducts() {
    return this.products;
  }

  public Map<Long, Order> getOrders() {
    return this.orders;
  }

  public Map<Long, Map<Long, Order>> getCustomerOrders() {
    return this.customerOrders;
  }

  public long addCustomer(final String name, final String address, final String email) {
    final long id = this.atomicLong.incrementAndGet();
    final Customer customer = new Customer();
    customer.setName(name);
    customer.setAddress(address);
    customer.setEmail(email);
    customer.setId(id);
    this.customers.put(id, customer);
    return id;
  }

  public long addProduct(final String name, final double price) {
    final long id = this.atomicLong.incrementAndGet();
    final Product product = new Product();
    product.setId(id);
    product.setName(name);
    product.setPrice(price);
    this.products.put(id, product);
    return id;
  }

  public long addOrder(final long customerId, final long productId, final long quantity) {
    Product product = products.get(productId);
    final long orderId = this.atomicLong.incrementAndGet();
    final Order order =
        Order.newBuilder().withId(orderId).withCustomerId(customerId).withProductId(productId)
            .withQuantity(quantity).withTotalPrice(product.getPrice() * quantity).build();
    Map<Long, Order> orderMap = Maps.newHashMap();
    orderMap.put(orderId, order);
    this.customerOrders.put(customerId, orderMap);
    return orderId;
  }
}
