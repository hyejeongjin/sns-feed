package com.example.sns_feed.user.controller;

import com.example.sns_feed.user.dto.requestdto.RequestDto;
import com.example.sns_feed.user.dto.responsedto.ResponseDto;
import com.example.sns_feed.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 2025 04 08
     * 양재호
     * 전체 유저 조회 기능
     */
    @GetMapping
    public ResponseEntity<List<ResponseDto>> findUsers(
            @RequestParam(required = false) String userName
    ) {

        if(!(userName == null)) {
            userService.findUsersByEmail(userName);
        }

        List<ResponseDto> users = userService.findUsers();


        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * 2025 04 08
     * 양재호
     * 특정 유저 조회 기능
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> findUserById(@PathVariable Long id) {

        ResponseDto findUser = userService.findUserById(id);

        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }

    /**
     * 2025 04 08
     * 양재호
     * 유저 수정 기능
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDto> updateUser(
            @PathVariable Long id,
            @RequestBody RequestDto dto
    ) {

        ResponseDto updateUser = userService.updateUser(id, dto);

        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }
}
