package com.example.sns_feed.user.controller;

import com.example.sns_feed.user.dto.responsedto.ResponseDto;
import com.example.sns_feed.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<ResponseDto>> findUsers(
            @RequestParam(required = false) String email
    ) {

        List<ResponseDto> users = userService.findUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }



}
