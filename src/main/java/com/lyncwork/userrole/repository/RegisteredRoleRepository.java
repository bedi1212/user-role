package com.lyncwork.userrole.repository;

import com.lyncwork.userrole.model.domain.entity.user.RegisteredRoleEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RegisteredRoleRepository extends CrudRepository<RegisteredRoleEntity, Long> {

    List<RegisteredRoleEntity> findAllByUserId(@Param("userId")Long userId);

    void deleteById(@Param("userId")Long userId);
}
