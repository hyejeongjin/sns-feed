package com.example.sns_feed.common.exception.follow;

import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.ErrorCode;

public class FollowAccessDeniedException extends CustomException {
    public FollowAccessDeniedException() {
        super(ErrorCode.FOLLOW_ACCESS_DENIED);
    }
}
