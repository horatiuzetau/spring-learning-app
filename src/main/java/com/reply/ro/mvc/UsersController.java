package com.reply.ro.mvc;


import com.reply.ro.models.Role;
import com.reply.ro.models.User;
import com.reply.ro.service.impl.RoleServiceImpl;
import com.reply.ro.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UsersController {

    @Autowired private UserServiceImpl userService;

    @Autowired private RoleServiceImpl roleService;


    @GetMapping(path = {"/profile", "/profile/edit"})
    public User getUser(Principal principal){
        return userService.getUser(principal);
    }

    @PutMapping("/profile/edit")
    public ResponseEntity<?> updateUser(@RequestBody User user, BindingResult result){
        User userUpdated = userService.updateUser(user);

        if(result.hasErrors()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        if(userUpdated == null){
            return new ResponseEntity<>("Something went wrong. Maybe the id was not correct!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }


//    DEBUGGING


    @GetMapping("/manage/users/all")
    public List<User > getAll0Users(){
        return userService.getAllUsers();
    }

    @GetMapping("/manage/roles/all")
    public List<Role > getAllRoles(){
        return roleService.getAllRoles();
    }

    @PostMapping("/manage/roles/add")
    public Role createRole(@RequestParam String roleName){
        return roleService.createRole(roleName);
    }



}
