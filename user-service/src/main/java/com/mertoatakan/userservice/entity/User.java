package com.mertoatakan.userservice.entity;

import com.mertoatakan.userservice.enums.Gender;
import com.mertoatakan.userservice.enums.UserStatus;
import com.mertoatakan.userservice.general.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User")
    @SequenceGenerator(name = "User", sequenceName = "USER_ID_SEQ")
    private Long id;

    @Column(name = "NAME", length = 100, nullable = false)
    @NotNull
    private String name;

    @Column(name = "SURNAME", length = 100, nullable = false)
    @NotNull
    private String surname;

    @Column(name = "EMAIL", nullable = false)
    @NotBlank(message = "Email cannot be blank.")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\+90\\d{10}$", message = "Invalid phone number")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "LATITUDE", nullable = false)
    @NotNull
    @DecimalMin(value = "-90.0", inclusive = true, message = "The value should be greater than -90.0")
    @DecimalMax(value = "90.0", inclusive = true, message = "The value should be smaller than 90.0")
    private Double latitude;

    @Column(name = "LONGITUDE", nullable = false)
    @NotNull
    @DecimalMin(value = "-180.0", inclusive = true, message = "The value should be greater than -180.0")
    @DecimalMax(value = "180.0", inclusive = true, message = "The value should be smaller than 180.0")
    private Double longitude;

    @Column(name = "PASSWORD", nullable = false)
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", nullable = true)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    @NotNull(message = "Status should not be null!")
    private UserStatus userStatus;

}
