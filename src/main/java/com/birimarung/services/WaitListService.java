package com.birimarung.services;

import com.birimarung.data.Waitlist;
import com.birimarung.dto.RestResponse;
import com.birimarung.repository.WaitListRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class WaitListService {
    private final WaitListRepository waitListRepository;

    public ResponseEntity<RestResponse<?>> addPersonToWaitList(String email) {
        Optional<Waitlist> optionalWaitlist = waitListRepository.findByEmail(email);
        RestResponse<?> restResponse = new RestResponse<>();

        if (optionalWaitlist.isPresent()) {
            restResponse.setMessage("User with email " + email + " already exists!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(restResponse);
        } else {
            Waitlist waitlist = new Waitlist();
            waitlist.setEmail(email);
            waitListRepository.save(waitlist);

            restResponse.setMessage("User with email " + email + " added successfully to waitlist");
            return ResponseEntity.status(HttpStatus.CREATED).body(restResponse);
        }
    }
}
