package io.github.bhuwanupadhyay.order;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static io.github.bhuwanupadhyay.order.OrderServiceApplication.DB_TABLE_PREFIX;

@Getter
@Entity
@Table(name = DB_TABLE_PREFIX + "order_line")
public class OrderLine {

    @Id
    private String itemId;
    private Integer quantity;
    @ManyToOne
    private Order order;
}
