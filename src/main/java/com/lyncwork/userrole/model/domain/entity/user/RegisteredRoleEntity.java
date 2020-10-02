package com.lyncwork.userrole.model.domain.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "registeredrole")
@Getter
@Setter
public class RegisteredRoleEntity {

    @Id
    @GeneratedValue
    @Setter(value = AccessLevel.NONE)
    private long id;

    @Column(name= "userid")
    private long userId;

    @Column(name= "roleid")
    private long roleId;

}
