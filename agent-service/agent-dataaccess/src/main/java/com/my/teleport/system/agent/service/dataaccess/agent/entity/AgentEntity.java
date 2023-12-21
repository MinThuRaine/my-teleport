package com.my.teleport.system.agent.service.dataaccess.agent.entity;


import com.my.teleport.system.domain.valueobject.AgentStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agents")
@Entity
public class AgentEntity {

    @Id
    private String id;

    private String name;

    private Integer age;

    private Integer status;

    private String identitynumber;

    private Integer phoneno;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgentEntity that = (AgentEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
