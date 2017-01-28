package io.tguduru.response;

import io.tguduru.model.Product;

/**
 * @author Guduru, Thirupathi Reddy
 * @modified 1/27/17
 */
public class OrderDetails {
  private long orderId;
  private long quantity;
  private Product product;
  private double totalPrice;

  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }

  public long getQuantity() {
    return quantity;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }
}
