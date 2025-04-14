package com.example.sns_feed.common.exception.follow;

import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.ErrorCode;

public class InvalidFollowRequestException extends CustomException {
    public InvalidFollowRequestException() {
        super(ErrorCode.INVALID_FOLLOW_REQUEST);
    }
}
