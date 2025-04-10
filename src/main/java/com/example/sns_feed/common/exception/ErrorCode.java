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

    //User
    USER_NOT_FOUND(404, "Not Found", "U001", "요청한 유저 정보를 찾을 수 없습니다."),
    PASSWORD_MISMATCH(400, "Bad Request", "U002", "패스워드가 일치하지 않습니다."),
    SAME_PASSWORD(400, "Bad Request", "U003", "기존 패스워드와 동일합니다."),

    //Board


    //Comment
    COMMENT_NOT_FOUND(404, "Not Found", "C001", "요청한 댓글을 찾을 수 없습니다."),
    USER_MISMATCH(403, "Forbidden", "C002", "작성자만 수정 가능합니다.");


    private final int status;
    private final String error;
    private final String code;
    private final String message;

}
