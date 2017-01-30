package io.tguduru.model;

/**
 * Product Model
 *
 * @author Guduru, Thirupathi Reddy
 * @modified 1/26/17
 */
public class Product {
  private long id;
  private String name;
  private double price;

  public long getId() {
    return this.id;
  }

  public void setId(final long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(final double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Product{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append(", price=").append(price);
    sb.append('}');
    return sb.toString();
  }
}
