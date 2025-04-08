package com.example.sns_feed.user.controller;

import com.example.sns_feed.common.MessageResponseDto;
import com.example.sns_feed.user.dto.requestdto.RequestDto;
import com.example.sns_feed.user.dto.responsedto.ResponseDto;
import com.example.sns_feed.user.entity.User;
import com.example.sns_feed.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDto> signup(
            @RequestBody RequestDto dto){
         //HttpSession session = request.getSession();
        //session.setAttribute("test_Id", messageResponseDto);
        return new ResponseEntity<>(userService.signup(dto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<MessageResponseDto> login(
            @RequestBody RequestDto dto,
            HttpSession session){
        return new ResponseEntity(userService.Login(dto), HttpStatus.OK);
    }
    @PostMapping("/logout")
    public ResponseEntity<MessageResponseDto> logout(
            @RequestBody RequestDto dto,
            HttpSession session){
        return new ResponseEntity(userService.Login(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResponseDto>> findUsers(
            @RequestParam(required = false) String email
    ) {

        List<ResponseDto> users = userService.findUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/withdraw")
    public ResponseEntity<MessageResponseDto>delete(@RequestBody RequestDto dto){
        return new ResponseEntity<>(userService.delete(dto.getEmail(), dto.getPassword()), HttpStatus.OK);
    }

}
