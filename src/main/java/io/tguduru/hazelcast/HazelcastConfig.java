package io.tguduru.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import io.tguduru.model.Customer;
import io.tguduru.model.Product;
import io.tguduru.serialize.CustomerSerializer;
import io.tguduru.serialize.ProductSerializer;

/**
 * @author Guduru, Thirupathi Reddy
 * @modified 1/27/17
 */
public class HazelcastConfig {
  public HazelcastInstance getHazelcast() {
    final Config config = new Config();
    final SerializerConfig customerSerializerConfig = new SerializerConfig();
    customerSerializerConfig.setClassName("customer").setImplementation(new CustomerSerializer())
        .setTypeClass(Customer.class);
    final SerializerConfig productSerializerConfig = new SerializerConfig();
    customerSerializerConfig.setClassName("product").setImplementation(new ProductSerializer())
        .setTypeClass(Product.class);
    SerializationConfig serializerConfigs = config.getSerializationConfig();
    serializerConfigs.addSerializerConfig(customerSerializerConfig);
    serializerConfigs.addSerializerConfig(productSerializerConfig);
    config.setSerializationConfig(serializerConfigs);
    final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
    // this.customers = hazelcastInstance.getMap("customers");
    // this.orders = hazelcastInstance.getMap("orders");
    // this.products = hazelcastInstance.getMap("products");
    // this.customerOrders = hazelcastInstance.getMap("customerOrders");

    return hazelcastInstance;
  }
}
