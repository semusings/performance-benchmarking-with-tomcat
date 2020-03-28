package io.github.bhuwanupadhyay.order;


import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class DeliveryAddress {

    private String address;
    private String mapData;
}
