package com.devsu.api.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateClientRequest {
    @JsonIgnore
    private Long id;
    private String name;
    private String gender;
    @Positive(message = "Age must be greater than 0")
    private Integer age;
    private String personId;
    private String address;
    private String phone;
    private String password;
    private String clientId;
}
