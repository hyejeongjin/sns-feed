package com.example.sns_feed.domain.user.service;

import com.example.sns_feed.common.MessageResponseDto;
import com.example.sns_feed.domain.user.dto.requestdto.*;
import com.example.sns_feed.domain.user.dto.responsedto.ResponseDto;
import com.example.sns_feed.domain.user.dto.responsedto.UserResponseDto;

import java.util.List;

public interface UserService {

    boolean existsByEmail(String email);

    MessageResponseDto signup(RequestDto dto);

    UserResponseDto login(LoginRequestDto dto);

    void updatePassword(UpdatePasswordRequestDto dto, Long id);

    void verifyEmailCode(String email, String code);

    void resetPassword(ChangePasswordRequestDto dto);

    void delete (UserResponseDto loginUser, String password);

    List<ResponseDto> findUsers();

    ResponseDto findUserById(Long id);

    ResponseDto updateUser(Long id, UpdateUserRequestDto dto);

    List<ResponseDto> findUsersByUserName(String userName);

}
