package com.my.teleport.system.client.service.dataaccess.client.repository;

import com.my.teleport.system.client.service.dataaccess.client.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientJpaRepository extends JpaRepository<ClientEntity, UUID> {
}
