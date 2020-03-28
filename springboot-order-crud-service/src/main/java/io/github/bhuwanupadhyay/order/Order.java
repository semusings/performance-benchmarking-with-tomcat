package io.github.bhuwanupadhyay.order;

import lombok.Getter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static io.github.bhuwanupadhyay.order.OrderServiceApplication.DB_TABLE_PREFIX;

@Getter
@Entity
@Table(name = DB_TABLE_PREFIX + "order")
public class Order {

    @Id
    private String orderId;
    private String customerId;
    @OneToMany(mappedBy = "order")
    @Cascade(CascadeType.ALL)
    private List<OrderLine> orderLines;
    private DeliveryAddress deliveryAddress;
    private LocalDateTime orderedAt;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
