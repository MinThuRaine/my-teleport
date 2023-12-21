package com.my.teleport.system.taskorder.service.dataaccess.order.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Entity
public class OrderEntity {

    @Id
    @Column(name = "id ")
    private String id;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "tracking_id")
    private String trackingId;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "status")
    private Integer orderStatus;

    @Column(name = "phoneno")
    private Integer phoneNo;

    @Column(name = "remark")
    private String remark;

    @Column(name = "created_date")
    private ZonedDateTime createdDate;

    @Column(name = "processed_date")
    private ZonedDateTime processedDate;

    @Column(name = "cancelled_date")
    private ZonedDateTime cancelledDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<OrderAddressEntity> orderAddress;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<OrderItemEntity> items;


    public void initializeLazyCollections() {
        checkAndFetchLazyCollection(this.items);
        checkAndFetchLazyCollection(this.orderAddress);
    }

    private void checkAndFetchLazyCollection(Collection lazyCollection){
        if (lazyCollection != null) {
            lazyCollection.size();
        }
    }



}
