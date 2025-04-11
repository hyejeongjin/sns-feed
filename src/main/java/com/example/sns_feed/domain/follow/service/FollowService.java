package com.example.sns_feed.domain.follow.service;


import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.follow.*;
import com.example.sns_feed.domain.follow.dto.requestdto.FollowRequestDto;
import com.example.sns_feed.domain.follow.dto.requestdto.FollowRequestListDto;
import com.example.sns_feed.domain.follow.dto.requestdto.RespondFollowRequestDto;
import com.example.sns_feed.domain.follow.dto.responsedto.FollowResponseDto;
import com.example.sns_feed.domain.follow.dto.responsedto.MyFriendsResponseDto;
import com.example.sns_feed.domain.follow.entity.Follow;
import com.example.sns_feed.domain.follow.enums.FollowStatus;
import com.example.sns_feed.domain.follow.repository.FollowRepository;
import com.example.sns_feed.domain.user.entity.User;
import com.example.sns_feed.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;


    /**
     * 팔로우 요청을 처리하는 메서드
     *
     * @param requestDto 팔로우 요청 DTO
     * @param senderId 요청을 보낸 사용자 ID
     * @return 저장된 팔로우 응답 DTO
     * @throws CustomException 유효하지 않은 요청, 중복 요청, 이미 친구인 경우 예외 발생
     */
    @Transactional
    public FollowResponseDto followRequest(FollowRequestDto requestDto, Long senderId) throws CustomException {
        User user = userRepository.findUserByIdOrElseThrow(senderId);
        User receiver = userRepository.findUserByIdOrElseThrow(requestDto.getReceiverId());

        // 자기 자신한테 신청 못하도록
        if (receiver.getId().equals(user.getId())){
            throw new InvalidFollowRequestException();
        }

        // 팔로우 신청 했던 사람한테 못하도록
        if (followRepository.pendingRequest(user, receiver)){
            throw new AlreadyRequestedFollowException();
        }
        // 이미 친구인 상태인 유저한테 못하도록
        if (followRepository.alreadyFollowing(user, receiver)) {
            throw new AlreadyFollowingException();
        }

        FollowStatus status = FollowStatus.PENDING;

        Follow follow = new Follow(user, receiver, status);

        Follow savedFollow = followRepository.save(follow);

        return new FollowResponseDto(savedFollow);
    }

    /**
     * 팔로우 요청 수락 또는 거절 처리
     *
     * @param followId 팔로우 요청 ID
     * @param request 수락 또는 거절 정보가 담긴 DTO
     * @param loginUserID 로그인한 사용자 ID
     * @throws CustomException 권한이 없거나, 이미 처리된 요청일 경우 예외 발생
     */
    @Transactional
    public void respondFollowRequest(Long followId, RespondFollowRequestDto request, Long loginUserID) throws CustomException {
        Follow follow = followRepository.findById(followId).orElseThrow(FollowRequestNotFoundException::new);

        // 수락, 거절은 receiver(요청 받은자)만 가능
        if(!follow.getReceiver().getId().equals(loginUserID)){
            throw new FollowAccessDeniedException();
        }

        // 이미 요청을 응답했을 경우
        if (!follow.getFollowStatus().equals(FollowStatus.PENDING)){
            throw new FollowRequestAlreadyHandledException();
        }

        if(request.getAccept()){
            // 요청 수락
            follow.updateStatus(FollowStatus.ACCEPTED);
        } else if (request.getReject()) {
            // 요청 거절
            follow.updateStatus(FollowStatus.REJECTED);
        } else {
            throw new BadRequestException(); // 다른 입력을 요청 했을때
        }
    }

    /**
     * 로그인한 사용자의 친구 목록 조회
     *
     * @param senderId 사용자 ID
     * @return 친구 목록 DTO 리스트
     */
    public List<MyFriendsResponseDto> getMyFriends(Long senderId) {
        List<User> userList = followRepository.findAcceptedFollowingsBySenderId(senderId);
        return userList.stream().map(MyFriendsResponseDto::new).toList();
    }

    /**
     * 나에게 팔로우 요청을 보낸 사용자 목록 조회
     *
     * @param receiverId 요청을 받은 사용자 ID
     * @return 팔로우 요청 리스트 DTO
     */
    public List<FollowRequestListDto> getPendingFollowRequests(Long receiverId){
        List<Follow> followRequestList = followRepository.findPendingSenderByReceiverId(receiverId);
        return followRequestList.stream().map(FollowRequestListDto::new).toList();
    }

    /**
     * 친구 삭제 처리
     *
     * @param senderId 요청한 사용자 ID
     * @param receiverId 삭제할 친구의 사용자 ID
     * @throws CustomException 친구가 아닌 경우 예외 발생
     */
    @Transactional
    public void receiverIdDelete(Long senderId, Long receiverId) throws CustomException {
        boolean following = followRepository.alreadyFollowing(
                userRepository.findUserByIdOrElseThrow(senderId),
                userRepository.findUserByIdOrElseThrow(receiverId)
        );

        if (!following){
            throw new NotFriendException();
        }

        followRepository.deleteFollow(senderId,receiverId);
    }

}
