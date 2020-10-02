package com.lyncwork.userrole.model.domain.entity.user;

public enum UserRole {
    ADMINISTRATOR("Administrator"),
    DEVELOPER("Developer");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName(){
        return roleName;
    }
}
