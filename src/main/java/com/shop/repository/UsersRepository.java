package com.shop.repository;

import com.shop.domain.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users,Long> {
    @Override
    Users save(Users users);
}
