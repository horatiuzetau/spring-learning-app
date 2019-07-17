package com.reply.ro.service;

import com.reply.ro.models.Role;

import java.util.List;

public interface RoleService{

//    ADD ROLE
        Role createRole(String roleName);

//    GET ALL
        List<Role > getAllRoles();
}
