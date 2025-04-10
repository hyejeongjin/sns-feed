package com.example.sns_feed.common.exception.follow;

import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.ErrorCode;

public class AlreadyFollowingException extends CustomException {
    public AlreadyFollowingException() {
        super(ErrorCode.ALREADY_FOLLOWING);
    }
}
