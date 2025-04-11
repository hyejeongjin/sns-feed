package com.example.sns_feed.common.exception.follow;

import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.ErrorCode;

public class AlreadyRequestedFollowException extends CustomException {
    public AlreadyRequestedFollowException() {
        super(ErrorCode.ALREADY_REQUESTED_FOLLOW);
    }
}
