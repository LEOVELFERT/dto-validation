package com.dto_validation.dto_validation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    @NotNull(message = "User Name should not be null")
    private String name;

    @NotNull(message = "mail id should not be null")
    @Email(regexp = "^[a-zA-Z0-9+_.-]+@gmail.com",message = "email id should be valid")
    private String email;

    @Min(value = 18,message = "age should not be below 18")
    @Max(value = 35,message = "age should not bigger than 35")
    private int age;

    @Pattern(regexp = "BACHELOR|MASTER|DOCTORATE",message = "Degree is invalid")
    private String degree;

    @Size(max=100,min = 10,message ="url is invalid")
    private String url;

    @UniqueElements(message = "bookTitles should be unique")
    private List<String> bookTitles;
}
