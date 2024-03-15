package com.mertoatakan.userservice.mapper;

import com.mertoatakan.userservice.dto.UserDTO;
import com.mertoatakan.userservice.dto.UserReviewDTO;
import com.mertoatakan.userservice.entity.User;
import com.mertoatakan.userservice.entity.UserReview;
import com.mertoatakan.userservice.enums.Rate;
import com.mertoatakan.userservice.request.UserReviewSaveRequest;
import com.mertoatakan.userservice.request.UserSaveRequest;
import com.mertoatakan.userservice.request.UserUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserReviewMapper {

    UserReviewMapper INSTANCE = Mappers.getMapper(UserReviewMapper.class);

    @Mapping(target = "reviewDate", ignore = true)
    @Mapping(target = "rate", expression = "java(integerToRate(request.rate()))")
    UserReview convertToUserReview(UserReviewSaveRequest request);

    @Mapping(target = "rate", source = "rate")
    UserReviewDTO convertToUserReviewDTO(UserReview userReview);

    List<UserReviewDTO> convertToUserReviewDTOs(List<UserReview> userReviews);

    default Integer rateToInteger(Rate rate) {
        if (rate == null) {
            return null;
        }
        return rate.getValue();
    }

    default Rate integerToRate(Integer value) {
        if (value == null) {
            return null;
        }
        return Rate.fromValue(value);
    }

}
