package com.example.sns_feed.common.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "Bad Request", "C001", "유효하지 않은 입력 값입니다."),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed", "C002", "허용되지 않은 요청 방식입니다."),
    ENTITY_NOT_FOUND(400, "Bad Request", "C003", "요청한 엔티티를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, "Server Error", "C004", "내부 서버 오류가 발생했습니다."),
    INVALID_TYPE_VALUE(400, "Bad Request", "C005", "유효하지 않은 타입의 값입니다."),

    //Auth
    INVALID_EMAIL(400, "Bad Request", "A001", "이미 가입된 이메일입니다."),
    SESSION_NOT_FOUND(404, "Not Found", "A002", "세션이 만료되었습니다."),
    CODE_MISMATCH(400, "Bad Request", "A003", "인증 번호가 일치하지 않습니다."),
    //User
    USER_NOT_FOUND(404, "Not Found", "U001", "요청한 유저 정보를 찾을 수 없습니다."),
    PASSWORD_MISMATCH(400, "Bad Request", "U002", "비밀번호가 일치하지 않습니다."),
    SAME_PASSWORD(400, "Bad Request", "U003", "기존 비밀번호와 동일합니다."),
    DELETED_USER(400, "Bad Request", "U004", "이미 삭제된 유저입니다."),
    INVALID_USER(401, "Unauthorized", "U005", "유저 정보가 일치하지 않습니다."),
    INVALID_SESSION(401, "Unauthorized", "U006", "세션이 유효하지 않습니다."),
    INVALID_CERT(400, "Bad Request", "U007", "cert가 일치하지 않습니다."),
    NEED_LOGIN(400, "Bad Request", "U008", "로그인 해주세요."),
    //Board
    BOARD_NOT_FOUND(404,"Not Found","B001","해당 게시글 번호가 존재하지 않습니다."),

    BOARD_UNAUTHORIZED(401,"Unauthorized","B002","게시글 작성자만 수정/삭제 할 수 있습니다.");

    //Comment



    private final int status;
    private final String error;
    private final String code;
    private final String message;

}
