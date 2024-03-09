package com.mertoatakan.userservice.general;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class BaseAdditionalFields {
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private Long creatorId;
    private Long updaterId;
}
