package com.projetoapptabacaria.webtabaca.view.controller;

import com.projetoapptabacaria.webtabaca.mapper.UserMapper;
import com.projetoapptabacaria.webtabaca.services.UserService;
import com.projetoapptabacaria.webtabaca.shared.UserDTO;
import com.projetoapptabacaria.webtabaca.view.controller.model.user.UserRequest;
import com.projetoapptabacaria.webtabaca.view.controller.model.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

        @Autowired
        private UserService userService;
        @Autowired
        private UserMapper userMapper;

        @GetMapping
        public ResponseEntity<List<UserResponse>> findAll(){
            //
            List<UserDTO> users = userService.findAll();

            List<UserResponse> response = users.stream()
                    .map(UserDTO -> userMapper.userDtoToUserResponse(UserDTO))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Optional<UserResponse>> findById(@PathVariable String id){
            //obtendo user pelo id
            Optional<UserDTO> users = userService.findById(id);

            if (users.isPresent()) {
                // Se o produto existir, realizar o mapeamento
                UserResponse UserResponse = userMapper.userDtoToUserResponse(users.get());
                return new ResponseEntity<>(Optional.of(UserResponse), HttpStatus.OK);
            } else {
                // Se o user não existir NOT_FOUND
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PostMapping
        public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userReq){ //request body ele tenta converter o que escrevemos em JSON em product

            UserDTO UserDTO = userMapper.userRequestToUserDTO(userReq);

            UserDTO = userService.addUser(UserDTO);

            UserResponse UserResponse = userMapper.userDtoToUserResponse(UserDTO);
            return new ResponseEntity<>(UserResponse, HttpStatus.CREATED);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteUser(@PathVariable String id){
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @PutMapping("/{id}")
        public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userReq, @PathVariable String id){ //request body pra converter o JSON em product
            UserDTO UserDTO = userMapper.userRequestToUserDTO(userReq);

            UserDTO = userService.updateUser(id, UserDTO);

            UserResponse UserResponse = userMapper.userDtoToUserResponse(UserDTO);

            return new ResponseEntity<>(UserResponse,HttpStatus.OK);

        }
}
