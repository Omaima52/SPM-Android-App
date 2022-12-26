package com.example.spm.controller;

import com.example.spm.dto.UserDTO;
import com.example.spm.service.IUserService;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    IUserService UserService;
    
    @PostMapping()
    public UserDTO postMethodName(@RequestBody UserDTO userDTO) {
        
        return UserService.createOrUpdateUSer(userDTO);
    }

    
    
}
