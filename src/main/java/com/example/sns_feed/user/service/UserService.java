package com.example.sns_feed.user.service;

import com.example.sns_feed.common.MessageResponseDto;
import com.example.sns_feed.user.dto.responsedto.ResponseDto;

import java.util.List;

public interface UserService {


    MessageResponseDto signup(String email, String password, String mobileNumber, String birthday);

    MessageResponseDto Login(String email, String password);

    ResponseDto findpassword(String email);

    void delete (String email, String password);

    List<ResponseDto> findUsers();
}
