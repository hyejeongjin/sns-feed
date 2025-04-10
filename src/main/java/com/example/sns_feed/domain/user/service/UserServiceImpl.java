package com.example.sns_feed.domain.user.service;

import com.example.sns_feed.common.MessageResponseDto;
import com.example.sns_feed.common.PasswordEncoder;
import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.ErrorCode;
import com.example.sns_feed.domain.user.dto.requestdto.LoginRequestDto;
import com.example.sns_feed.domain.user.dto.requestdto.RequestDto;
import com.example.sns_feed.domain.user.dto.requestdto.UpdatePasswordRequestDto;
import com.example.sns_feed.domain.user.dto.requestdto.UpdateUserRequestDto;
import com.example.sns_feed.domain.user.dto.responsedto.ResponseDto;
import com.example.sns_feed.domain.user.dto.responsedto.UserResponseDto;
import com.example.sns_feed.domain.user.entity.User;
import com.example.sns_feed.domain.user.repository.UserRepository;
import com.example.sns_feed.domain.user.dto.requestdto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 2025 04 08
     *  김형진
     *  해당 가입정보가 있는가?
     * @param email String data
     * @return bool
     */

    public boolean existsByEmail(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    /*
     * 202 04 07
     * 김형진
     * 가입
     * */
    @Override
    public MessageResponseDto signup(
            RequestDto dto) {

        if(existsByEmail(dto.getEmail())){
            throw new CustomException(ErrorCode.INVALID_EMAIL, "이미 가입되었던 정보입니다.");
        }

        User user = new User(dto);
        user.updatePassword( passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        //가입 완료 메세지를 보낸다.
        return new MessageResponseDto("가입 완료 되었습니다.");
    }

    /*
     * 202 04 07
     * 김형진
     * 로그인
     * */
    @Override
    public UserResponseDto login(LoginRequestDto dto) {

        User findUser = userRepository.findByEmailOrThrow(dto.getEmail());

        if(findUser.getDeletedAt() != null){
            throw new CustomException(ErrorCode.DELETED_USER, "로그인이 불가능한 이메일, 비밀번호 입니다.");
        }

        if (!passwordEncoder.matches( dto.getPassword(), findUser.getPassword())){
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }
        return new UserResponseDto(findUser.getId());
    }

    /*
     * 202 04 07
     * 김형진
     * 비밀번호 수정
     * */
    @Override
    public void updatePassword(UpdatePasswordRequestDto dto, Long id) {

        User findUser = userRepository.findUserByIdOrElseThrow(id);
        if (!passwordEncoder.matches( dto.getOldPassword(), findUser.getPassword())){

            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);

        }
        if(dto.getOldPassword().equalsIgnoreCase(dto.getNewPassword())) {
           throw new CustomException(ErrorCode.SAME_PASSWORD);
        }

        findUser.updatePassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(findUser);
    }

    /*
     * 202 04 07
     * 김형진
     * 비밀번호 삭제
     * 예외 수정할것.
     * */
    @Override
    public void delete(UserResponseDto  loginUser, String password) {

        User user = userRepository.findUserByIdOrElseThrow(loginUser.getId());
        if (!passwordEncoder.matches( password, user.getPassword())){

            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }
        user.updatedeletedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        userRepository.save(user);
    }

    /**
     * 2025 04 08
     * 양재호
     * 전체 유저 조회 기능
     */
    @Override
    public List<ResponseDto> findUsers() {

        List<User> findUser = userRepository.findAll();

        return findUser.stream().map(ResponseDto::toDto).toList();
    }

    /**
     * 2025 04 08
     * 양재호
     * 전체 유저 조회 기능(QueryString에 userName이 있는 경우)
     */
    @Override
    public List<ResponseDto> findUsersByUserName(String userName) {

        List<User> findUsers = userRepository.findUserByUserName(userName);

        return findUsers.stream().map(ResponseDto::toDto).toList();
    }

    /**
     * 2025 04 08
     * 양재호
     * 특정 유저 조회 기능
     */
    @Override
    public ResponseDto findUserById(Long id) {

        User findUser = userRepository.findUserByIdOrElseThrow(id);

        return new ResponseDto(findUser);
    }

    /**
     * 2025 04 08
     * 양재호
     * 유저 수정 기능
     */
    @Transactional
    @Override
    public ResponseDto updateUser(Long id, UpdateUserRequestDto dto) {

        User findUser = userRepository.findUserByIdOrElseThrow(id);

        findUser.updateUser(dto);

        User updatedUser = userRepository.save(findUser);

        return new ResponseDto(updatedUser);
    }
}
