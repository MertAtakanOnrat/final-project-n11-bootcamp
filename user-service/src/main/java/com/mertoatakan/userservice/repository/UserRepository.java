package com.mertoatakan.userservice.repository;

import com.mertoatakan.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByIdAndName(Long id, String name);

    void deleteUserById(Long id);

}
