package com.mertoatakan.userservice.service.entityService;

import com.mertoatakan.userservice.entity.User;
import com.mertoatakan.userservice.errormessage.UserErrorMessage;
import com.mertoatakan.userservice.exceptions.ItemNotFoundException;
import com.mertoatakan.userservice.exceptions.UserNotFoundException;
import com.mertoatakan.userservice.general.BaseEntityService;
import com.mertoatakan.userservice.general.GeneralErrorMessage;
import com.mertoatakan.userservice.mapper.UserMapper;
import com.mertoatakan.userservice.repository.UserRepository;
import com.mertoatakan.userservice.request.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityService extends BaseEntityService<User, UserRepository> {
    protected UserEntityService(UserRepository repository) {
        super(repository);
    }

    public User findByIdAndName(Long id, String name){
        if(findByIdWithControl(id).getName() != name){
            throw new UserNotFoundException(UserErrorMessage.USER_NOT_FOUND);
        }
        return getRepository().findByIdAndName(id, name);
    }

    public void deleteUserById(Long id){
        getRepository().deleteUserById(id);
    }

    @Override
    public User findByIdWithControl(Long id) {
        Optional<User> optionalE = getRepository().findById(id);
        User user;
        if (optionalE.isPresent()){
            user = optionalE.get();
        }
        else{
            throw new UserNotFoundException(UserErrorMessage.USER_NOT_FOUND);
        }
        return user;
    }
}
