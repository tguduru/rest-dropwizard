package io.tguduru.model;

/**
 * Order Model
 *
 * @author Guduru, Thirupathi Reddy
 * @modified 1/26/17
 */
public class Order {
  private long id;
  private long customerId;
  private long productId;
  private long quantity;
  private double totalPrice;

  public Order() {}

  public long getId() {
    return id;
  }

  public long getCustomerId() {
    return customerId;
  }

  public long getProductId() {
    return productId;
  }

  public long getQuantity() {
    return quantity;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  private Order(final Builder builder) {
    this.id = builder.id;
    this.customerId = builder.customerId;
    this.productId = builder.productId;
    this.quantity = builder.quantity;
    this.totalPrice = builder.totalPrice;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Order{");
    sb.append("id=").append(id);
    sb.append(", customerId=").append(customerId);
    sb.append(", productId=").append(productId);
    sb.append(", quantity=").append(quantity);
    sb.append(", totalPrice=").append(totalPrice);
    sb.append('}');
    return sb.toString();
  }

  public static Builder newBuilder() {
    return new Builder();
  }


  public static final class Builder {
    private long id;
    private long customerId;
    private long productId;
    private long quantity;
    private double totalPrice;

    private Builder() {}

    public Builder withId(final long val) {
      this.id = val;
      return this;
    }

    public Builder withCustomerId(final long val) {
      this.customerId = val;
      return this;
    }

    public Builder withProductId(final long val) {
      this.productId = val;
      return this;
    }

    public Builder withQuantity(final long val) {
      this.quantity = val;
      return this;
    }

    public Builder withTotalPrice(final double val) {
      this.totalPrice = val;
      return this;
    }

    public Order build() {
      return new Order(this);
    }
  }
}
