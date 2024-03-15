package com.mertoatakan.userservice.service.entityService;

import com.mertoatakan.userservice.entity.User;
import com.mertoatakan.userservice.entity.UserReview;
import com.mertoatakan.userservice.errormessage.UserErrorMessage;
import com.mertoatakan.userservice.errormessage.UserReviewErrorMessage;
import com.mertoatakan.userservice.exceptions.UserNotFoundException;
import com.mertoatakan.userservice.exceptions.UserReviewNotFoundException;
import com.mertoatakan.userservice.general.BaseEntityService;
import com.mertoatakan.userservice.repository.UserReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserReviewEntityService extends BaseEntityService<UserReview, UserReviewRepository> {
    protected UserReviewEntityService(UserReviewRepository repository) {
        super(repository);
    }

    public List<UserReview> findAllByRestaurantId(String id){
        return getRepository().findAllByRestaurantId(id);
    }

    public List<UserReview> findAllByUserId(Long id){
        return getRepository().findAllByUserId(id);
    }

    @Override
    public UserReview findByIdWithControl(Long id) {
        Optional<UserReview> optionalE = getRepository().findById(id);
        UserReview userReview;
        if (optionalE.isPresent()){
            userReview = optionalE.get();
        }
        else{
            throw new UserReviewNotFoundException(UserReviewErrorMessage.USER_REVIEW_NOT_FOUND);
        }
        return userReview;
    }
}
