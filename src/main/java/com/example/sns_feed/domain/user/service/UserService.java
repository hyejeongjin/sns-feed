package com.example.sns_feed.domain.user.service;

import com.example.sns_feed.common.MessageResponseDto;
import com.example.sns_feed.domain.user.dto.requestdto.*;
import com.example.sns_feed.domain.user.dto.responsedto.ResponseDto;
import com.example.sns_feed.domain.user.dto.responsedto.UserResponseDto;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {


    boolean existsByEmail(String email);

    MessageResponseDto signup(@RequestBody RequestDto dto);

    UserResponseDto login(@RequestBody LoginRequestDto dto);

    void updatePassword(@RequestBody UpdatePasswordRequestDto dto, Long id);

    void checkingCode(String email, String code);
    void updateNewPassword(@RequestBody ChangePasswordRequestDto dto);

    void delete (UserResponseDto loginUser, String password);

    List<ResponseDto> findUsers();

    ResponseDto findUserById(Long id);

    ResponseDto updateUser(Long id, UpdateUserRequestDto dto);

    List<ResponseDto> findUsersByUserName(String userName);

    void changePassword(@Valid ChangePasswordRequestDto dto, String email);
}
