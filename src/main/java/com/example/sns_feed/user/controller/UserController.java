package com.example.sns_feed.user.controller;

import com.example.sns_feed.common.Const;
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

/**
 * 2025 04 08
 * 양재호
 * 전제 기능에 session기준 추가해야함(로그인 되었을 경우에 조회가능~)
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDto> signup(
            @RequestBody RequestDto dto){
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

    /**
     * 2025 04 08
     * 양재호
     * 전체 유저 조회 기능
     */
    @GetMapping
    public ResponseEntity<List<ResponseDto>> findUsers(
            @RequestParam(required = false) String userName
    ) {

        if (!(userName == null)) {
            List<ResponseDto> users = userService.findUsersByEmail(userName);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            List<ResponseDto> users = userService.findUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
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

    @DeleteMapping("/withdraw")
    public ResponseEntity<MessageResponseDto>delete(@RequestBody RequestDto dto){
        return new ResponseEntity<>(userService.delete(dto.getEmail(), dto.getPassword()), HttpStatus.OK);
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
