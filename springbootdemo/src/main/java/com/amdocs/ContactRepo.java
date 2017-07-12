package com.amdocs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

/**
 * Created by atinsingh on 6/22/17.
 */

@RepositoryRestResource(collectionResourceRel = "contact", path = "contact")
public interface ContactRepo extends JpaRepository<Contact,Long> {

    Collection<Contact> findContactsById(int id);
}
