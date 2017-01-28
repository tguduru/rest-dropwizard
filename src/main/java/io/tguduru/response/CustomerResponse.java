package io.tguduru.response;

import java.util.Map;

import io.tguduru.model.Customer;

/**
 * @author Guduru, Thirupathi Reddy
 * @modified 1/27/17
 */
public class CustomerResponse {
  private Customer customer;
  private Map<Long, OrderDetails> orderMap;

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Map<Long, OrderDetails> getOrderMap() {
    return orderMap;
  }

  public void setOrderMap(Map<Long, OrderDetails> orderMap) {
    this.orderMap = orderMap;
  }
}
