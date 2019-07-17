package com.reply.ro.service.impl;


import com.reply.ro.models.Role;
import com.reply.ro.repository.RoleRepository;
import com.reply.ro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired private RoleRepository roleRepository;

    @Override
    public Role createRole(String roleName){
        Role role = new Role();
        role.setName(roleName);

        return roleRepository.save(role);
    }

    @Override
    public List<Role > getAllRoles(){
        return roleRepository.findAll();
    }

}
