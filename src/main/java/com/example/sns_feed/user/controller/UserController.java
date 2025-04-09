package com.example.sns_feed.user.controller;

import com.example.sns_feed.common.Const;
import com.example.sns_feed.common.MessageResponseDto;
import com.example.sns_feed.user.dto.requestdto.LoginRequestDto;
import com.example.sns_feed.user.dto.requestdto.RequestDto;
import com.example.sns_feed.user.dto.requestdto.UpdatePasswordRequestDto;
import com.example.sns_feed.user.dto.responsedto.ResponseDto;
import com.example.sns_feed.user.dto.responsedto.UserResponseDto;
import com.example.sns_feed.user.entity.User;
import com.example.sns_feed.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 2025 04 08
 * 양재호
 * 전제 기능에 session기준 추가해야함(로그인 되었을 경우에 조회가능~)
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    /**
     * 2025 04 08
     * 김형진
     * 회워 가입
     * @param dto
     * @return 정상가입 메제지 출력
     */
    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDto> signup(
            @RequestBody RequestDto dto){
        return new ResponseEntity<>(userService.signup(dto), HttpStatus.OK);
    }

    /**
     * 2025 04 08
     * 김형진
     * 로그인
     * @param dto
     * @param request
     * @return  정상로그인 메세지 출력
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String,String >> login(
            @RequestBody LoginRequestDto dto,
            HttpServletRequest request){

        //이전에 탈퇴했던 회원인가?
        UserResponseDto UserResponseDto = userService.login(dto);
        HttpSession session = request.getSession();
        session.setAttribute(Const.LOGIN_USER,UserResponseDto);

        return new ResponseEntity<>(Map.of("message", "로그인에 성공하였습니다."),HttpStatus.OK);
    }




    @PostMapping("/logout")
    public ResponseEntity<Map<String,String >> logOut(
            HttpServletRequest request
    ){
        // 로그인하지 않으면 HttpSession이 null로 반환된다.
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();// 해당 세션(데이터)을 삭제한다.
        }
        return new ResponseEntity<>(Map.of("message", "로그아웃이 정상 처리 되었습니다."),HttpStatus.ACCEPTED);
    }
    /**
     * 2025 04 08
     * 김형진
     * @param dto
     * @return
     */
    @PatchMapping("/updatePassword")
    public ResponseEntity<Map<String,String>>updatePasswrd(
            @RequestBody UpdatePasswordRequestDto dto,
            @SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser){
        userService.updatePassword(dto, loginUser.getId());
        return new ResponseEntity<>(Map.of("message", "비밀번호 변경을 성공하였습니다."),HttpStatus.OK);
    }

    /**
     * 2025 04 07
     * 김형진
     * @param dto
     * @return
     */
    @DeleteMapping("/withdraw")
    public ResponseEntity<Map<String,String>>delete(
            @RequestBody RequestDto dto,
            @SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser
    ){
        userService.delete(loginUser, dto.getPassword());
        return new ResponseEntity<>(Map.of("message", "회원 탈퇴 성공하였습니다."),HttpStatus.OK);
    }
    /**
     * 2025 04 08
     * 양재호
     * 전체 유저 조회 기능
     */
    @GetMapping
    public ResponseEntity<List<ResponseDto>> findUsers(
            @RequestParam(required = false) String userName
    ) {

        if (!(userName == null)) {
            List<ResponseDto> users = userService.findUsersByEmail(userName);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            List<ResponseDto> users = userService.findUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }


    /**
     * 2025 04 08
     * 양재호
     * 특정 유저 조회 기능
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> findUserById(@PathVariable Long id) {

        ResponseDto findUser = userService.findUserById(id);

        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }


    /**
     * 2025 04 08
     * 양재호
     * 유저 수정 기능
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDto> updateUser(
            @PathVariable Long id,
            @RequestBody RequestDto dto
    ) {

        ResponseDto updateUser = userService.updateUser(id, dto);

        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

}
