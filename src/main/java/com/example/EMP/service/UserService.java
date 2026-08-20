package com.example.EMP.service;

import com.example.EMP.Entity.User;
import com.example.EMP.dto.UserResponseDTO;
import com.example.EMP.dto.userRequestDTO;
import com.example.EMP.repository.UserRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    //register user
    public UserResponseDTO registerUser(userRequestDTO urdto) {

        User user = new User();
        //user.setId(urdto.getId());
        user.setName(urdto.getName());
        user.setEmail(urdto.getEmail());
        user.setPassword(encoder.encode(urdto.getPassword()));

        User u = userRepository.save(user);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(u.getId());
        userResponseDTO.setName(u.getName());
        userResponseDTO.setEmail(u.getEmail());

        return userResponseDTO;
    }

    //get user
    public UserResponseDTO getuser(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(u.getId());
        userResponseDTO.setName(u.getName());
        userResponseDTO.setEmail(u.getEmail());

        return userResponseDTO;
    }

    //delete user
    public void deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            throw new OpenApiResourceNotFoundException(
                    "Employee not found with id: " + id
            );
        }

        userRepository.deleteById(id);
    }

    //update user
    public void updatePassword(Long id, String password) {
        User u = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
        String encodedPassword = encoder.encode(password);
        u.setPassword(encodedPassword);
        userRepository.save(u);
    }

}


