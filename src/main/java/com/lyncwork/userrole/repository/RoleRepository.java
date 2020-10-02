package com.lyncwork.userrole.repository;

import com.lyncwork.userrole.model.domain.entity.user.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

}
