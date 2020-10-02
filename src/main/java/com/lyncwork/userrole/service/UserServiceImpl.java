package com.lyncwork.userrole.service;

import com.lyncwork.userrole.exception.BadRequest;
import com.lyncwork.userrole.model.domain.entity.user.RegisteredRoleEntity;
import com.lyncwork.userrole.model.domain.entity.user.RoleEntity;
import com.lyncwork.userrole.model.domain.entity.user.UserEntity;
import com.lyncwork.userrole.repository.RegisteredRoleRepository;
import com.lyncwork.userrole.repository.RoleRepository;
import com.lyncwork.userrole.repository.UserRepository;
import com.lyncwork.userrole.request.UserRequest;
import com.lyncwork.userrole.response.RoleItems;
import com.lyncwork.userrole.response.UserResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisteredRoleRepository registeredRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserResponse> getAllUser() {
        List<UserResponse> userResponse = new ArrayList<>();
        List<UserEntity> users =  (List<UserEntity>) userRepository.findAll();
        if(users != null){
            List<RoleEntity> roles = (List<RoleEntity>) roleRepository.findAll();
            for(UserEntity user : users){
                userResponse.add(getUpdateResponse(user, roles));
            }
        }
        return userResponse;
    }

    @Override
    public  UserResponse getUserById(long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        UserResponse response = new UserResponse();
        if(user != null){
            List<RoleEntity> roles = (List<RoleEntity>) roleRepository.findAll();
            response = getUpdateResponse(user, roles);
        }
        return response;
    }

    @Override
    public void deleteUser(long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        if (user== null){
            throw new BadRequest("no data found");
        }
        registeredRoleRepository.deleteById(user.getId());
        userRepository.deleteById(user.getId());
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        return saveUpdateUser(userRequest, new UserEntity());
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest, long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        if(userEntity == null){
            throw new BadRequest("no data found");
        }
        return saveUpdateUser(userRequest, userEntity);
    }

    private UserResponse getUpdateResponse(UserEntity user, List<RoleEntity> roles){ ;
        List<RegisteredRoleEntity> registeredRoles = registeredRoleRepository.findAllByUserId(user.getId());
        List<RoleItems> userRoles = new ArrayList<>();
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        for (RegisteredRoleEntity registeredRole : registeredRoles){
            RoleItems roleItems = new RoleItems();
            roleItems.setId(registeredRole.getRoleId());
            roleItems.setName(roles.stream().filter(r -> r.getId() == registeredRole.getRoleId()).map(r -> r.getRole().getRoleName()).findFirst().get());
            userRoles.add(roleItems);
        }
        response.setRoles(userRoles);
        return response;
    }

    private UserResponse saveUpdateUser(UserRequest userRequest, UserEntity userEntity){
        userEntity.setFirstName(userRequest.getFirstName());
        userEntity.setLastName(userRequest.getLastName());
        userEntity = userRepository.save(userEntity);

        List<RoleEntity> roles = (List<RoleEntity>) roleRepository.findAll();
        List<Long> roleIds = roles.stream().filter(r -> userRequest.getRoles().contains(r.getRole().getRoleName())).map(RoleEntity::getId).collect(Collectors.toList());
        List<RoleItems> roleItemsList = new ArrayList<>();
        for (Long roleId : roleIds){
            RoleItems roleItems = new RoleItems();
            roleItems.setId(roleId);
            roleItems.setName(roles.stream().filter(r -> r.getId()==roleId).map(r -> r.getRole().getRoleName()).findFirst().get());
            RegisteredRoleEntity registeredRoleEntity = new RegisteredRoleEntity();
            registeredRoleEntity.setUserId(userEntity.getId());
            registeredRoleEntity.setRoleId(roleId);
            registeredRoleRepository.save(registeredRoleEntity);
            roleItemsList.add(roleItems);
        }

        UserResponse response = new UserResponse();
        response.setRoles(roleItemsList);
        response.setId(userEntity.getId());
        response.setFirstName(userRequest.getFirstName());
        response.setLastName(userRequest.getLastName());
        return response;
    }
}
