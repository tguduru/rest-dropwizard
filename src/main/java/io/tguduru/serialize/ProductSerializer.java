package io.tguduru.serialize;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

import io.tguduru.model.Product;

/**
 * @author Guduru, Thirupathi Reddy
 * @modified 1/27/17
 */
public class ProductSerializer implements StreamSerializer<Product> {
  @Override
  public void write(ObjectDataOutput out, Product product) throws IOException {
    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    final XMLEncoder xmlEncoder = new XMLEncoder(outputStream);
    xmlEncoder.writeObject(product);
    xmlEncoder.close();
    out.write(outputStream.toByteArray());
  }

  @Override
  public Product read(ObjectDataInput in) throws IOException {
    final InputStream inputStream = (InputStream) in;
    final XMLDecoder xmlDecoder = new XMLDecoder(inputStream);
    return (Product) xmlDecoder.readObject();
  }

  @Override
  public int getTypeId() {
    return 1;
  }

  @Override
  public void destroy() {

  }
}
