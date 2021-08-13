package com.gabriel.gonoring.question.app.user.controller;

import com.gabriel.gonoring.question.app.user.dto.NewUserDTO;
import com.gabriel.gonoring.question.app.user.dto.UpdatedUserDTO;
import com.gabriel.gonoring.question.app.user.dto.UsersSearchFiltersDTO;
import com.gabriel.gonoring.question.app.user.dto.UserDTO;
import com.gabriel.gonoring.question.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getUsers(UsersSearchFiltersDTO filters, Pageable pageable){
        return ResponseEntity.ok(userService.getUsers(filters, pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") UUID id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody @Valid NewUserDTO newUserDTO){
        userService.saveUser(newUserDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody @Valid UpdatedUserDTO userDTO){
        userService.updateUser(userDTO);

        return new ResponseEntity(HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") UUID id){
        userService.deleteUserById(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
