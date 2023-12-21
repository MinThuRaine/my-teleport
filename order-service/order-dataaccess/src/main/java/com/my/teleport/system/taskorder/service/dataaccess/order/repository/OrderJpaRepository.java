package com.my.teleport.system.taskorder.service.dataaccess.order.repository;

import com.my.teleport.system.taskorder.service.dataaccess.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, String> {

    @Modifying
    @Query("UPDATE OrderEntity SET orderStatus=:status, processedDate =:confirmedDate  WHERE id=:id")
    Integer processOrder(@Param("status") Integer status, @Param("id") String id, @Param("confirmedDate") ZonedDateTime confirmedDate);


    @Modifying
    @Query("UPDATE OrderEntity SET orderStatus=:status, cancelledDate =:rejectedDate  WHERE id=:id")
    Integer cancelOrder(@Param("status") Integer status, @Param("id") String id, @Param("rejectedDate") ZonedDateTime rejectedDate);


}
