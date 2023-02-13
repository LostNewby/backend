package com.naturalgoods.backend.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RequestUserDto {

    private Long id;

    @NotNull(message = "firstName cannot be empty")
    @Size(min = 1, max = 200, message
            = "firstName must be between 1 and 200 characters")
    private String firstName;

    @NotNull(message = "lastName cannot be empty")
    @Size(min = 1, max = 200, message
            = "lastName must be between 1 and 200 characters")
    private String lastName;

    @NotNull(message = "Email cannot be empty")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email should be valid")
    private String email;

    @NotNull(message = "Phone cannot be empty")
    @Pattern(regexp = "^7[0-9]{10}$", message = "Phone should be valid")
    private String phoneNumber;

}
