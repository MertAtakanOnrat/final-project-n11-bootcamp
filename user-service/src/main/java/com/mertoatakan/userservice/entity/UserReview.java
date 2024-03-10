package com.mertoatakan.userservice.entity;

import com.mertoatakan.userservice.enums.Rate;
import com.mertoatakan.userservice.general.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_REVİEW")
public class UserReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserReview")
    @SequenceGenerator(name = "UserReview", sequenceName = "USER_REVIEW_ID_SEQ")
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    @NotNull
    private Long userId;

    @Column(name = "RESTAURANT_ID", nullable = false)
    @NotBlank
    private String restaurantId;

    @Column(name = "COMMENT", nullable = false)
    @NotBlank
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "RATE", nullable = false)
    @NotNull(message = "Rate cannot be null")
    private Rate rate;

    @Column(name = "REVIEW_DATE", nullable = false)
    @NotNull
    private LocalDateTime reviewDate;
}
