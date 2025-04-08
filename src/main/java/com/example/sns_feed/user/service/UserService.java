package com.example.sns_feed.user.service;

import com.example.sns_feed.common.MessageResponseDto;
import com.example.sns_feed.user.dto.requestdto.RequestDto;
import com.example.sns_feed.user.dto.responsedto.ResponseDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {


    MessageResponseDto signup(@RequestBody  RequestDto dto);

    MessageResponseDto Login(@RequestBody RequestDto dto);

    ResponseDto findpassword(String email);

    MessageResponseDto delete (String email, String password);

    List<ResponseDto> findUsers();

    ResponseDto findUserById(Long id);

    ResponseDto updateUser(Long id, RequestDto dto);

    List<ResponseDto> findUsersByEmail(String userName);
}
