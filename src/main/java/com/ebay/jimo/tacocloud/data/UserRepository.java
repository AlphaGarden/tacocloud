package com.ebay.jimo.tacocloud.data;

import com.ebay.jimo.tacocloud.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
