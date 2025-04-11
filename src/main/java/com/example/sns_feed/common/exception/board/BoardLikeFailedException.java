package com.example.sns_feed.common.exception.board;

import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.ErrorCode;

public class BoardLikeFailedException extends CustomException {

    public BoardLikeFailedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
