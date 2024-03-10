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
    @NotNull(message = "Email cannot be null")
    @Size(min = 5, max = 255, message = "Email must be between 5 and 255 characters")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\+90\\d{10}$", message = "Invalid phone number")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "LATITUDE", nullable = false)
    @NotNull
    @DecimalMin(value = "-90.0", inclusive = true)
    @DecimalMax(value = "90.0", inclusive = true)
    private Double latitude;

    @Column(name = "LONGITUDE", nullable = false)
    @NotNull
    @DecimalMin(value = "-180.0", inclusive = true)
    @DecimalMax(value = "180.0", inclusive = true)
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
    @NotNull
    private UserStatus userStatus;

}
