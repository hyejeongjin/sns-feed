package com.example.sns_feed.user.service;

import com.example.sns_feed.common.MessageResponseDto;
import com.example.sns_feed.user.dto.requestdto.RequestDto;
import com.example.sns_feed.user.dto.responsedto.ResponseDto;
import com.example.sns_feed.user.entity.User;
import com.example.sns_feed.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    /*
    * 202 04 07
    * 김형진
    * 로그인 체크
    * */
//    public Long userId(@SessionAttribute(name = Const.LOGIN_USER, required = false) MessageResponseDto checked) {
//        if(checked.)
//        return 0L;
//    }
    /*
     * 202 04 07
     * 김형진
     * 가입
     * */
    @Override
    public MessageResponseDto signup(RequestDto dto) {


        User findUser = userRepository.findByEmailOrThrow(dto.getEmail());
        if(findUser.getEmail().equals(dto.getEmail())){
            throw new DuplicateKeyException("이미 가입된 정보입니다.");
        }
        User user = new User(dto);
        User saveUser = userRepository.save(user);
        //가입 완료 메세지를 보낸다.
        return new MessageResponseDto("가입 완료 되었습니다.");
    }

    /*
     * 202 04 07
     * 김형진
     * 로그인
     * */
    @Override
    public MessageResponseDto Login(String email, String password) {

        User findUser = userRepository.findByEmailOrThrow(email);
        if (findUser.getEmail().equals(email)) {
            throw new DuplicateKeyException("이미 가입된 정보입니다.");
        }
        return null;
    }
    /*
     * 202 04 07
     * 김형진
     * 비밀번호 수정
     * */
    @Override
    public ResponseDto findpassword(String email) {
        //
        User findUser = userRepository.findByEmailOrThrow(email);
        return null;
    }
    /*
     * 202 04 07
     * 김형진
     * 비밀번호 삭제
     * 예외 수정할것.
     * */
    @Override
    public void delete(String email, String password) {
        User findUser = userRepository.findByEmailOrThrow(email);
        if(!findUser.equals(password)){
            throw new RuntimeException("입력한 패스워드와 다릅니다.");
        }
        userRepository.delete(findUser);
    }

    @Override
    public List<ResponseDto> findUsers() {

        List<User> findUser = userRepository.findAll();

        return findUser.stream().map(ResponseDto::toDto).toList();
    }

    @Override
    public ResponseDto findUserById(Long id) {

        User findUser = userRepository.findUserByIdOrElseThrow(id);

        return new ResponseDto(findUser);
    }

    @Override
    public ResponseDto updateUser(Long id, RequestDto dto) {

        User findUser = userRepository.findUserByIdOrElseThrow(id);

        findUser.updateUser(dto);

        User updatedUser = userRepository.save(findUser);

        return new ResponseDto(updatedUser);
    }
}
