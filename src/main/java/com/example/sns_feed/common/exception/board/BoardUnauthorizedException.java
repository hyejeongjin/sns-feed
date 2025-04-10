package com.example.sns_feed.common.exception.board;

import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.ErrorCode;

public class BoardUnauthorizedException extends CustomException {
    public BoardUnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BoardUnauthorizedException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}