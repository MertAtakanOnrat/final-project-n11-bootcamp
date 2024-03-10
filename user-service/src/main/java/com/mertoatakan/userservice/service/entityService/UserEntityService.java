package com.mertoatakan.userservice.service.entityService;

import com.mertoatakan.userservice.entity.User;
import com.mertoatakan.userservice.general.BaseEntityService;
import com.mertoatakan.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService extends BaseEntityService<User, UserRepository> {
    protected UserEntityService(UserRepository repository) {
        super(repository);
    }
}
