package com.example.sns_feed.user.service;

import com.example.sns_feed.user.dto.responsedto.ResponseDto;

import java.util.List;

public interface UserService {


    List<ResponseDto> findUsers();
}
