package com.example.EMP.controller;

import com.example.EMP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.EMP.dto.*;


@RestController
@RequestMapping("/user")
class UserController{

    @Autowired
    public UserService userService;

    //insert employee
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody userRequestDTO URDTO){
        return ResponseEntity.ok(userService.registerUser(URDTO));
    }

    //get by id
    @GetMapping("/getuser/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getuser(id));
    }

    //delete user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    //update user
    @PutMapping("/update/{id}")
    public ResponseEntity<Void>updateUser(@PathVariable Long id, @RequestBody updatePassRequest uprest){
        userService.updatePassword(id,uprest.getPassword());
        return ResponseEntity.ok().build();
    }
}