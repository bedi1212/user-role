package com.lyncwork.userrole.model.domain.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "role")
@Getter
@Setter
public class RoleEntity {
    @Id
    @GeneratedValue
    @Setter(value= AccessLevel.NONE)
    private long id;

    @Column(name = "rolename")
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
