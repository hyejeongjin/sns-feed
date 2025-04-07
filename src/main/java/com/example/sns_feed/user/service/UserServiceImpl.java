package com.example.sns_feed.user.service;

import com.example.sns_feed.user.dto.responsedto.ResponseDto;
import com.example.sns_feed.user.entity.User;
import com.example.sns_feed.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<ResponseDto> findUsers() {

        List<User> findUser = userRepository.findAll();

//        return findUser.stream().map(ResponseDto::toDto).toList();
        return  null;
    }
}
