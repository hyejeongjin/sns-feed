package com.example.sns_feed.common.exception.follow;

import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.ErrorCode;

public class FollowRequestNotFoundException extends CustomException {
    public FollowRequestNotFoundException() {
        super(ErrorCode.FOLLOW_REQUEST_NOT_FOUND);
    }
}
