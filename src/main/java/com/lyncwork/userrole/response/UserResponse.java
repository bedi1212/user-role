package com.lyncwork.userrole.response;

import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private long id;
    private String firstName;
    private String lastName;

    private List<RoleItems> roles;

}
