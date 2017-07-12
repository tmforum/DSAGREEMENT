package com.amdocs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by atinsingh on 6/22/17.
 */
@Component
public class ContactCommandLine implements CommandLineRunner {

    @Autowired
    ContactRepo contactRepo;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Calling the test");
        Contact contact = new Contact("Atin", "Toronto");
        Contact contact1 = new Contact("Manish","Kansas");

        contactRepo.save(contact);
        contactRepo.save(contact1);

        for (Contact con : this.contactRepo.findAll()){
            System.out.println(con.getContactName());
        }
    }
}
