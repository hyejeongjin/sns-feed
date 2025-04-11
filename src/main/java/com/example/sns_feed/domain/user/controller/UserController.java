package com.example.sns_feed.domain.user.controller;

import com.example.sns_feed.common.Const;
import com.example.sns_feed.common.MessageResponseDto;
import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.ErrorCode;
import com.example.sns_feed.domain.email.emailservice.EmailService;
import com.example.sns_feed.domain.user.dto.requestdto.*;
import com.example.sns_feed.domain.user.dto.requestdto.LoginRequestDto;
import com.example.sns_feed.domain.user.dto.requestdto.RequestDto;
import com.example.sns_feed.domain.user.dto.requestdto.UpdatePasswordRequestDto;
import com.example.sns_feed.domain.user.dto.responsedto.ResponseDto;
import com.example.sns_feed.domain.user.dto.responsedto.UserResponseDto;
import com.example.sns_feed.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2025 04 08
 * 양재호
 * 전제 기능에 session기준 추가해야함(로그인 되었을 경우에 조회가능~)
 */

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EmailService emailService;

    /**
     * 2025 04 08
     * 김형진
     * 회워 가입
     * @param dto
     * @return 정상가입 메제지 출력
     */
    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDto> signup(
           @Valid @RequestBody RequestDto dto) {
        return new ResponseEntity<>(userService.signup(dto), HttpStatus.OK);
    }

    /**
     * 2025 04 08
     * 김형진
     * 로그인
     * @param dto
     * @param request
     * @return 정상로그인 메세지 출력
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String,String >> login(
            @Valid @RequestBody LoginRequestDto dto,
            HttpServletRequest request){

        //이전에 탈퇴했던 회원인가?
        UserResponseDto UserResponseDto = userService.login(dto);
        HttpSession session = request.getSession();
        session.setAttribute(Const.LOGIN_USER, UserResponseDto);

        return new ResponseEntity<>(Map.of("message", "로그인에 성공하였습니다."), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(
            HttpServletRequest request
    ) {

        // 로그인하지 않으면 HttpSession이 null로 반환된다.
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();// 해당 세션(데이터)을 삭제한다.
        }
        return new ResponseEntity<>(Map.of("message", "로그아웃이 정상 처리 되었습니다."), HttpStatus.ACCEPTED);
    }

    /**
     * 2025 04 08
     * 김형진
     * @param dto
     * @return
     */
    @PatchMapping("/updatePassword")
    public ResponseEntity<Map<String, String>> updatePassword(
            @Valid @RequestBody UpdatePasswordRequestDto dto,
            @SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser) {
        userService.updatePassword(dto, loginUser.getId());
        return new ResponseEntity<>(Map.of("message", "비밀번호 변경을 성공하였습니다."), HttpStatus.OK);
    }


    //비번 찾기1


    //본인인증2
    //비번 초기화

    //새 비번 입력3

//    @PatchMapping("/findPassword")
//    public ResponseEntity<Map<String, String>> findPassword(
//            @Valid @RequestBody UpdatePasswordRequestDto dto) {
//
//        userService.updatePassword(dto, loginUser.getId());
//        return new ResponseEntity<>(Map.of("message", "비밀번호 변경을 성공하였습니다."), HttpStatus.OK);
//    }

    /**
     * 2025 04 07
     * 김형진
     * @param dto
     * @return
     */
    @DeleteMapping("/withdraw")
    public ResponseEntity<Map<String, String>> delete(
            @Valid @RequestBody WithdrawRequestDto dto,
            @SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser
    ) {
        userService.delete(loginUser, dto.getPassword());
        return new ResponseEntity<>(Map.of("message", "정상적으로 회원 탈퇴 성공하였습니다."), HttpStatus.OK);
    }

    /**
     * 2025 04 08
     * 양재호
     * 전체 유저 조회 기능
     */
    @GetMapping("/users")
    public ResponseEntity<List<ResponseDto>> findUsers(
            @RequestParam(required = false) String userName
    ) {

        if (!(userName == null)) {
            List<ResponseDto> users = userService.findUsersByUserName(userName);
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
    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseDto> findUserById(
            @PathVariable Long id
    ) {

        ResponseDto findUser = userService.findUserById(id);

        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }


    /**
     * 2025 04 08
     * 양재호
     * 유저 수정 기능
     */
    @PatchMapping("/users/{id}")
    public ResponseEntity<ResponseDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequestDto dto,
            @SessionAttribute(name = Const.LOGIN_USER, required = false) UserResponseDto loginUser
    ) {

        if (id != loginUser.getId()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ResponseDto updateUser = userService.updateUser(loginUser.getId(), dto);

        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    /**
     * 2025 04 10
     * 양재호
     * @param dto
     * @param request
     * @return
     */

    // 이메일 보내는 메서드
    // 여기선 유저 검증하고 맞으면 이메일 보냄
    // 그리고 session 발급하고 CERTNum 저장해
    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(
            @Valid @RequestBody EmailRequestDto dto,
            HttpServletRequest request){

        boolean existsByEmail = userService.existsByEmail(dto.getEmail());

        // 본인인증 -> 여기서 하지말고 updatePassword 에서 session발급하고?
        if(!existsByEmail) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        // EmailService 추가
        // email 있는 requestDto 추가
        // 이메일 쏘며 생성했던 난수 저장
        int num = emailService.sendMail(dto.getEmail());

        // int num 을 String으로!
        String certNum = String.valueOf(num);

        Map<String, String> valueMap = new HashMap<>();

        valueMap.put("cert", certNum);
        valueMap.put("email", dto.getEmail());

        // String으로 변환한 certNum을 value에 저장하고 이걸 통과해야 비번변경 가능하게?
        HttpSession session = request.getSession();
        session.setAttribute("cert", valueMap);

        // 세션 만료 시간 3분으로 설정
        session.setMaxInactiveInterval(180);

        return new ResponseEntity<>("메일을 전송하였습니다.", HttpStatus.OK);
    }

    /**
     * 2025 04 10
     * 양재호
     * @param dto
     * @param httpServletRequest
     * @return
     */
    // 패스워드 초기화
    @PostMapping("/check")
    public ResponseEntity<String> check(
            @Valid @RequestBody CheckRequestDto dto,
            HttpServletRequest httpServletRequest
            ) {

        HttpSession session = httpServletRequest.getSession(false);

        if(session == null || session.getAttribute("cert") == null) {
            throw new CustomException(ErrorCode.INVALID_SESSION);
        }

        Map<String, String> valueMap = (Map<String, String>) session.getAttribute("cert");
        String email = valueMap.get("email");

        if(!valueMap.get("cert").equals(dto.getCert())) {
            throw new CustomException(ErrorCode.INVALID_CERT);
        }

        // 비밀번호 초기화 -> 근데 cert Session만으로는 DB에 접근을 못하잖아?
        // 어떻게 해야할까?
        session.setAttribute("changePassword", email);

        return new ResponseEntity<>("본인 인증에 성공하였습니다.", HttpStatus.OK);
    }

    /**
     * 2025 04 10
     * 양재호
     * @param dto
     * @param httpServletRequest
     * @return
     */
    @PatchMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @Valid @RequestBody ChangePasswordRequestDto dto,
            HttpServletRequest httpServletRequest
    ) {
        // 아? 그러고보니 새 패스워드 받아오는 건 맞는데 여기서 DB에 어케 접근하지??
        // CERT 세션에서 email, CERT를 Map 형태로 저장하고
        // 그 중에 email만 꺼내서 changePassword에 저장하고
        // 여기서 email을 꺼내서 DB에 접근하면? 가능하지 않나?
        HttpSession session = httpServletRequest.getSession(false);

        if (session == null || session.getAttribute("changePassword") == null) {
            throw new CustomException(ErrorCode.INVALID_SESSION);
        }

        // String으로 저장했는데 왜 Object 형식인거지ㅣ....?
        String email = (String) session.getAttribute("changePassword");

        userService.changePassword(dto, email);

        session.invalidate();
        return new ResponseEntity<>("비밀번호가 변경되었습니다.", HttpStatus.OK);
    }
}

//아.........난 왜이렇게 멍청한것인가........................................................................
// 1. 비번 찾기
// 2. 본인인증 / 비번 초기화
// 3. 새 비번 입력