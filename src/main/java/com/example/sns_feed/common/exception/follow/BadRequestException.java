package com.example.sns_feed.common.exception.follow;

import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.ErrorCode;

public class BadRequestException extends CustomException {
    public BadRequestException() {
        super(ErrorCode.BAD_REQUEST);
    }
}
