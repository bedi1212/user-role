package com.lyncwork.userrole.model.domain.entity.user;

import com.lyncwork.userrole.model.domain.modal.user.UserModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity implements UserModel {
    @Id
    @GeneratedValue
    @Setter(value = AccessLevel.NONE)
    private long id;

    @Column(name= "firstname")
    private String firstName;

    @Column(name= "lastname")
    private String lastName;



}
