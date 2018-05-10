package com.smu.vaan.repository;

import com.smu.vaan.model.security.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    User findByUsername(String loginName);

}
