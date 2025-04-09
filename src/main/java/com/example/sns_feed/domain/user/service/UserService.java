package com.example.sns_feed.domain.user.service;

import com.example.sns_feed.common.MessageResponseDto;
import com.example.sns_feed.domain.user.dto.requestdto.LoginRequestDto;
import com.example.sns_feed.domain.user.dto.requestdto.RequestDto;
import com.example.sns_feed.domain.user.dto.requestdto.UpdatePasswordRequestDto;
import com.example.sns_feed.domain.user.dto.requestdto.UpdateUserRequestDto;
import com.example.sns_feed.domain.user.dto.responsedto.ResponseDto;
import com.example.sns_feed.domain.user.dto.responsedto.UserResponseDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {


    boolean existsByEmail(String email);

    MessageResponseDto signup(@RequestBody  RequestDto dto);

    UserResponseDto login(@RequestBody LoginRequestDto dto);

    void updatePassword(@RequestBody UpdatePasswordRequestDto dto, Long id);

    void delete (UserResponseDto loginUser, String password);

    List<ResponseDto> findUsers();

    ResponseDto findUserById(Long id);

    ResponseDto updateUser(Long id, UpdateUserRequestDto dto);

    List<ResponseDto> findUsersByUserName(String userName);
}
