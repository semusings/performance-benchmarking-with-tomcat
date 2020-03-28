package io.github.bhuwanupadhyay.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderEndpoints {

    private final OrderRepository repository;

    @GetMapping
    public List<Order> getOrders() {
        return (List<Order>) repository.findAll();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return repository.save(order);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable("orderId") String orderId, @RequestBody Order order) {
        return repository.findById(orderId)
                .map(repository::save)
                .map(o -> ResponseEntity.ok().body(o))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Object> deleteOrder(@PathVariable("orderId") String orderId) {
        return repository.findById(orderId)
                .map(order -> {
                    repository.delete(order);
                    return null;
                })
                .map(o -> ResponseEntity.ok().body(null))
                .orElse(ResponseEntity.notFound().build());
    }

}
