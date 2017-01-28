package io.tguduru.serialize;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

import io.tguduru.model.Customer;

/**
 * @author Guduru, Thirupathi Reddy
 * @modified 1/26/17
 */
public class CustomerSerializer implements StreamSerializer<Customer> {
  @Override
  public void write(final ObjectDataOutput out, final Customer customer) throws IOException {
    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    final XMLEncoder xmlEncoder = new XMLEncoder(outputStream);
    xmlEncoder.writeObject(customer);
    xmlEncoder.close();
    out.write(outputStream.toByteArray());
  }

  @Override
  public Customer read(final ObjectDataInput in) throws IOException {
    final InputStream inputStream = (InputStream) in;
    final XMLDecoder xmlDecoder = new XMLDecoder(inputStream);
    return (Customer) xmlDecoder.readObject();
  }

  @Override
  public int getTypeId() {
    return 1;
  }

  @Override
  public void destroy() {

  }
}
