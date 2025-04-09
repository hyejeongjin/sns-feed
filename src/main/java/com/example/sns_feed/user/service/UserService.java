package com.example.sns_feed.user.service;

import com.example.sns_feed.common.MessageResponseDto;
import com.example.sns_feed.user.dto.requestdto.LoginRequestDto;
import com.example.sns_feed.user.dto.requestdto.RequestDto;
import com.example.sns_feed.user.dto.requestdto.UpdatePasswordRequestDto;
import com.example.sns_feed.user.dto.responsedto.ResponseDto;
import com.example.sns_feed.user.dto.responsedto.UserResponseDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {


    boolean existsByEmail(String email);

    MessageResponseDto signup(@RequestBody  RequestDto dto);

    UserResponseDto login(@RequestBody LoginRequestDto dto);

    MessageResponseDto updatePassword(@RequestBody UpdatePasswordRequestDto dto, Long id);

    MessageResponseDto delete (UserResponseDto loginUser, String password);

    List<ResponseDto> findUsers();

    ResponseDto findUserById(Long id);

    ResponseDto updateUser(Long id, RequestDto dto);

    List<ResponseDto> findUsersByEmail(String userName);
}
