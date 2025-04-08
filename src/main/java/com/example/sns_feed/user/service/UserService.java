package com.example.sns_feed.user.service;

import com.example.sns_feed.common.MessageResponseDto;
import com.example.sns_feed.user.dto.requestdto.RequestDto;
import com.example.sns_feed.user.dto.responsedto.ResponseDto;

import java.util.List;

public interface UserService {


    MessageResponseDto signup(RequestDto dto);

    MessageResponseDto Login(String email, String password);

    ResponseDto findpassword(String email);

    void delete (String email, String password);

    List<ResponseDto> findUsers();

    ResponseDto findUserById(Long id);

    ResponseDto updateUser(Long id, RequestDto dto);

    List<ResponseDto> findUsersByEmail(String userName);
}
