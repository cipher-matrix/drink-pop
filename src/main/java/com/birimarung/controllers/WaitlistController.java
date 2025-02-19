package com.birimarung.controllers;

import com.birimarung.dto.RestResponse;
import com.birimarung.services.WaitListService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/waitlist")
public class WaitlistController {
    private final WaitListService waitListService;

    @PostMapping("/{email}")
    public RestResponse<?> addUserController(@PathVariable String email) {
        return waitListService.addPersonToWaitList(email);
    }
}
