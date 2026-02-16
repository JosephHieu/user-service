package com.josephhieu.springsecurity.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreationRequest {

    @Size(min = 3, message = "UserName must have at least 3 digits")
    private String userName;

    @Size(min = 6, message = "Password must be at lest 6 characters")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
