package com.lyncwork.userrole.service;

import com.lyncwork.userrole.request.UserRequest;
import com.lyncwork.userrole.response.UserResponse;
import java.util.List;

public interface UserService {

    List<UserResponse> getAllUser();

    UserResponse getUserById(long id);

    void deleteUser(long id);

    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(UserRequest userRequest, long userId);
}
