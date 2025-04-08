package com.example.sns_feed.user.controller;

import com.example.sns_feed.common.MessageResponseDto;
import com.example.sns_feed.user.dto.requestdto.RequestDto;
import com.example.sns_feed.user.dto.responsedto.ResponseDto;
import com.example.sns_feed.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDto> signup(
            @RequestBody RequestDto dto,
            HttpServletRequest request){
        MessageResponseDto messageResponseDto = userService.signup(dto);
        HttpSession session = request.getSession();
        session.setAttribute("test_Id", messageResponseDto);
        return ResponseEntity.ok(new MessageResponseDto("환영입니다."));
    }

    @GetMapping
    public ResponseEntity<List<ResponseDto>> findUsers(
            @RequestParam(required = false) String email
    ) {

        List<ResponseDto> users = userService.findUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
