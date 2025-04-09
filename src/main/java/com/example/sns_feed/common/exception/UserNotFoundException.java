package com.example.sns_feed.common.exception;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(String message) {
        super(ErrorCode.USER_NOT_FOUND, message);
    }
}
