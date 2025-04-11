package com.example.sns_feed.common.exception.board;

import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.ErrorCode;

public class BoardLikeNotFoundException extends CustomException {
    public BoardLikeNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BoardLikeNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
