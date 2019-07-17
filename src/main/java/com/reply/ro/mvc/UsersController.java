package com.reply.ro.mvc;


import com.reply.ro.models.Role;
import com.reply.ro.models.User;
import com.reply.ro.service.impl.RoleServiceImpl;
import com.reply.ro.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User updateUser(@RequestBody User user){
       return userService.updateUser(user);
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
