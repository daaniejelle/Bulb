package com.example.restservice.repository;

import java.util.List;

import com.example.restservice.Model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {

    List<AppUser> findByuserName(String userName);

}