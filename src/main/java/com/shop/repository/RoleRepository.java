package com.shop.repository;

import com.shop.domain.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<UserRole, Long>{
    @Override
    UserRole save(UserRole userRole);
}
