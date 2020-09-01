package com.example.restservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.restservice.Model.AppUser;
import com.example.restservice.exeption.ResourceNotFoundException;
import com.example.restservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")

public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/appUsers")
    public List<AppUser> getAllAppUsers() {
        return (List<AppUser>) userRepository.findAll();
    }

    @GetMapping("/appUsers/{userId}")
    public ResponseEntity<AppUser> getAppUserByUserId(@PathVariable(value = "userId") Long userId)
            throws ResourceNotFoundException {
        AppUser appUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(appUser);
    }

    @PostMapping("/appUsers")
    public AppUser createAppUser(@Valid @RequestBody AppUser appUser) {
        return userRepository.save(appUser);
    }

    @PutMapping("/appUsers/{userId}")
    public ResponseEntity<AppUser> updateAppUser(@PathVariable(value = "userId") Long userId,
                                                 @Valid @RequestBody AppUser appUserDetails) throws ResourceNotFoundException {
        AppUser appUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        appUser.setUserId(appUserDetails.getUserId());
        appUser.setUserName(appUserDetails.getUserName());
        appUser.setEncrytedPassword(appUserDetails.getEncrytedPassword());

        final AppUser updatedAppUser = userRepository.save(appUser);
        return ResponseEntity.ok(updatedAppUser);
    }

    @DeleteMapping("/appUsers/{userId}")
    public Map<String, Boolean> deleteAppUser(@PathVariable(value = "userId") Long userId)
            throws ResourceNotFoundException {
        AppUser appUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(appUser);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
