package com.amdocs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by atinsingh on 6/22/17.
 */
@RestController

public class ContactRestController {

    @Autowired
    ContactRepo repo;

    @RequestMapping(name = "/test")
    public Collection<Contact> getAllContact(){
        return this.repo.findAll();
    }
}
