package io.github.bhuwanupadhyay.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;

import java.io.InputStream;
import java.util.stream.Stream;

@SpringBootApplication
public class OrderServiceApplication extends SpringBootServletInitializer {

    public static final String DB_TABLE_PREFIX = "app_";

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    @Lazy
    private OrderRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @EventListener
    @SneakyThrows
    public void initializeOrderService(ApplicationReadyEvent event) {
        Order[] orders = new ObjectMapper().readValue(readFile("Orders.json"), Order[].class);
        Stream.of(orders).forEach(repository::save);
    }

    private InputStream readFile(String file) {
        return this.getClass().getClassLoader().getResourceAsStream(file);
    }

}
