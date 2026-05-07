package com.fitness.userservice.serviceImpl;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.models.Users;
import com.fitness.userservice.repository.UserRepository;
import com.fitness.userservice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserResponse register(RegisterRequest request) {


        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User Already exits!!");
        }

        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setFirstName (request.getFirstName());
        user.setLastName (request.getLastName());
        user.setPassword(request.getPassword());
//        User savedUser = new User();


        Users savedUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setPassword(savedUser.getPassword());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setCreatedAt(savedUser.getCreatedAt());
        userResponse.setModifiedAt(savedUser.getModifiedAt());

        return userResponse;
    }

    @Override
    public ResponseEntity<UserResponse> getUser(String userId) {

        Users user = userRepository.findById(userId).orElse(null);

        if(user == null){
            return ResponseEntity.notFound().build();
        }

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setPassword(user.getPassword());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setModifiedAt(user.getModifiedAt());

        return ResponseEntity.ok().body(userResponse);
    }
}
