package com.lyncwork.userrole.controller;

import com.lyncwork.userrole.exception.BadRequest;
import com.lyncwork.userrole.request.UserRequest;
import com.lyncwork.userrole.response.UserResponse;
import com.lyncwork.userrole.service.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<UserResponse> getAllUser() {
        return  userService.getAllUser();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable(value = "id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("")
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        validate(userRequest);
        return userService.createUser(userRequest);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@RequestBody UserRequest userRequest, @PathVariable(value = "id") Long id){
        validate(userRequest);
        return userService.updateUser(userRequest, id);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id){
        Map<String, Boolean> response = new HashMap<>();
        userService.deleteUser(id);
        response.put("deleted", true);
        return response;
    }

    public void validate(UserRequest userRequest){
        if(userRequest.getFirstName() == null || userRequest.getFirstName().equals("")){
           throw new BadRequest("firstName: First name is mandatory");
        }
        int i = 0;
        for(String role : userRequest.getRoles()){
            if(role == null || role.equals("")){
                throw new BadRequest("roles["+i+"].name: Name is mandatory");
            }
            i++;
        }
    }

}
