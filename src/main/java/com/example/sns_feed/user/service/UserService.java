package com.example.sns_feed.user.service;

import com.example.sns_feed.common.MessageResponseDto;
import com.example.sns_feed.user.dto.requestdto.RequestDto;
import com.example.sns_feed.user.dto.requestdto.UpdatePasswordRequestDto;
import com.example.sns_feed.user.dto.responsedto.ResponseDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {



    String getEmail(ResponseDto dto);

    boolean existsByEmail(String email);

    MessageResponseDto signup(@RequestBody  RequestDto dto);

    ResponseDto login(@RequestBody RequestDto dto);

    MessageResponseDto updatePassword(@RequestBody UpdatePasswordRequestDto dto, String email);

    MessageResponseDto delete (String email, String password);

    List<ResponseDto> findUsers();

    ResponseDto findUserById(Long id);

    ResponseDto updateUser(Long id, RequestDto dto);

    List<ResponseDto> findUsersByEmail(String userName);
}
