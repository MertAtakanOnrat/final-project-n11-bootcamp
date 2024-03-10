package com.mertoatakan.userservice.service.entityService;

import com.mertoatakan.userservice.entity.UserReview;
import com.mertoatakan.userservice.general.BaseEntityService;
import com.mertoatakan.userservice.repository.UserReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class UserReviewEntityService extends BaseEntityService<UserReview, UserReviewRepository> {
    protected UserReviewEntityService(UserReviewRepository repository) {
        super(repository);
    }
}
