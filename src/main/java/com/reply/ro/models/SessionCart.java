package com.reply.ro.models;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.Entity;


@Component
@Scope("session")
public class SessionCart extends Cart{

    public SessionCart(){
        super();
        this.setName("Session cart");
    }

}
