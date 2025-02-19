package com.birimarung.services;

import com.birimarung.data.Waitlist;
import com.birimarung.dto.RestResponse;
import com.birimarung.repository.WaitListRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class WaitListService {
    private final WaitListRepository waitListRepository;

    public RestResponse<?> addPersonToWaitList(String email) {
        Optional<Waitlist> optionalWaitlist = waitListRepository.findByEmail(email);
        RestResponse<?> restResponse = new RestResponse<>();
        Waitlist waitlist = new Waitlist();
        if (optionalWaitlist.isPresent()) {
            restResponse.setMessage("User with email " + email + " already exists");
            restResponse.setStatusCode(HttpStatus.CONFLICT.value());
        } else {
            waitlist.setEmail(email);
            waitListRepository.save(waitlist);
            restResponse.setMessage("User with email " + email + " added successfully to waitlist");
            restResponse.setStatusCode(HttpStatus.OK.value());
        }
        return restResponse;
    }
}
