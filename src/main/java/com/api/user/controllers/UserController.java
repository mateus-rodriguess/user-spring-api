package com.api.user.controllers;

import com.api.user.dtos.UserDto;
import com.api.user.models.UserModel;
import com.api.user.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;


@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/user")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto){
        if (userService.existsByUsername(userDto.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: User name is already in use!");
        }
        if (userService.existByEmail(userDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Email is already in user!");
        }
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }
    @GetMapping
    public ResponseEntity<Page<UserModel>> getAllUser(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
    }
    @GetMapping("/username/{username}")
    public ResponseEntity getUsername(@PathVariable(value = "username") @NotNull String username){
        if (!username.isEmpty()){
            var userModelOptional = userService.existsByUsername(username);

            if (userModelOptional){
                return ResponseEntity.status(HttpStatus.OK).body(userService.findByUsername(username).get());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id){
        Optional<UserModel> userModelOptional = userService.findById(id);
        if (!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOneUser(@PathVariable(value = "id") UUID id){
        Optional<UserModel> userModelOptional = userService.findById(id);
        if(userModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
        userService.delete(userModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> puOneUser(@PathVariable(value = "id") UUID id, @RequestBody @Valid UserDto userDto){
        Optional <UserModel> userModelOptional = userService.findById(id);
        if (userModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setId(userModelOptional.get().getId());
        userModel.setCreatedAt(userModelOptional.get().getCreatedAt());
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));
    }

}
