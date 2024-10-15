package com.dto_validation.dto_validation.controller;

import com.dto_validation.dto_validation.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserDto userDto){
        return new ResponseEntity<>("User Created SuccesFully", HttpStatus.CREATED);
    }

}
