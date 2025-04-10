package com.example.sns_feed.common.exception.follow;

import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.ErrorCode;

public class NotFriendException extends CustomException {
    public NotFriendException() {
        super(ErrorCode.NOT_FRIEND);
    }
}
