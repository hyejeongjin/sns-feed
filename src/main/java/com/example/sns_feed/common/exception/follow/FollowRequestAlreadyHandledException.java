package com.example.sns_feed.common.exception.follow;

import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.ErrorCode;

public class FollowRequestAlreadyHandledException extends CustomException {
    public FollowRequestAlreadyHandledException() {
        super(ErrorCode.REQUEST_PROCESSED);
    }
}
