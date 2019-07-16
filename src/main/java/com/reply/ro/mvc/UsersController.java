package com.reply.ro.mvc;


import com.reply.ro.models.Role;
import com.reply.ro.models.User;
import com.reply.ro.service.impl.RoleServiceImpl;
import com.reply.ro.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;



    @PostMapping("/add-role")
    public Role createRole(@RequestBody Role role){
        return roleService.createRole(role);
    }

    @GetMapping("/roles")
    public List<Role > getAllRoles(){
        return roleService.getAllRoles();
    }

    @PostMapping("/add-user")
    public User createRole(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/users")
    public List<User > getAllUsers(){
        return userService.getAllUsers();
    }
}
