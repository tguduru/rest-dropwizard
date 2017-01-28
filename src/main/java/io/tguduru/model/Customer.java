package io.tguduru.model;

/**
 * CustomerResponse Model
 *
 * @author Guduru, Thirupathi Reddy
 * @modified 1/26/17
 */
public class Customer {
  private long id;
  private String name;
  private String address;
  private String email;


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

  public String getAddress() {
    return this.address;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }
}
