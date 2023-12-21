package com.my.teleport.system.agent.service.dataaccess.agent.repository;

import com.my.teleport.system.agent.service.dataaccess.agent.entity.AgentEntity;
import com.my.teleport.system.agent.service.domain.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AgentJpaRepository extends JpaRepository<AgentEntity, String> {

    @Modifying
    @Query("Update AgentEntity agent SET agent.status=:status WHERE agent.id=:id")
    public int  updateAgentStatus(@Param("id") String id, @Param("status") Integer status);


}
