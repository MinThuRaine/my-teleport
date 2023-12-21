package com.my.teleport.system.client.service.dataaccess.client.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
@Entity
public class ClientEntity {

    @Id
    private String id;

    private String name;

    private Integer age;

    private Integer clientstatus;

    private String identitynumber;

    private Integer phoneno;

    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<ClientAddressEntity> address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
