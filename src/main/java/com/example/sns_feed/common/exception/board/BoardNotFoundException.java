package com.example.sns_feed.common.exception.board;

import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.ErrorCode;

public class BoardNotFoundException extends CustomException {

    public BoardNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BoardNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
