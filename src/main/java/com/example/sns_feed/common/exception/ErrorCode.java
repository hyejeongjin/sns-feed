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
    DELETED_USER(400, "Bad Request", "U004", "이미 삭제된 유저입니다."),

    //Board
    BOARD_NOT_FOUND(404,"Not Found","B001","해당 게시글 번호가 존재하지 않습니다."),
    BOARD_UNAUTHORIZED(401,"Unauthorized","B002","게시글 작성자만 수정/삭제 할 수 있습니다."),

    //BoardLike
    BOARD_LIKE_FAILED(400,"Bad Request","BL01","본인이 작성한 게시글에는 좋아요를 누를 수 없습니다."),
    BOARD_LIKE_NOT_FOUND(404,"Not Found","BL02","요청하신 게시글 좋아요 정보를 찾을 수 없습니다."),


    //Comment
    COMMENT_NOT_FOUND(404, "Not Found", "C001", "요청한 댓글을 찾을 수 없습니다."),
    USER_MISMATCH(403, "Forbidden", "C002", "작성자만 수정 가능합니다."),

    //follow
    UNAUTHORIZED(401,"Unauthorized","F001","로그인이 필요합니다."),
    INVALID_FOLLOW_REQUEST(400,"Bad Request","F002","자신에게는 요청이 안됩니다."),
    ALREADY_REQUESTED_FOLLOW(400,"Bad Request","F003","이미 팔로우 요청을 보냈습니다."),
    ALREADY_FOLLOWING(400,"Bad Request","F004","이미 친구 상태입니다."),
    FOLLOW_ACCESS_DENIED(403,"Forbidden","F005","팔로우 권한 요청이 없습니다."),
    NOT_FRIEND(400,"Bad Request","F006","친구 관계가 아닙니다."),
    REQUEST_PROCESSED(400,"Bad Request","F007","이미 처리된 요청입니다."),
    BAD_REQUEST(400,"Bad Request","F008","잘못된 요청 형식입니다."),
    FOLLOW_REQUEST_NOT_FOUND(400,"Bad Request","F009","해당 팔로우 요청을 찾을 수 없습니다.");



    private final int status;
    private final String error;
    private final String code;
    private final String message;

}
