package com.example.awswithspring.service;

import com.example.awswithspring.apiPayload.code.status.ErrorStatus;
import com.example.awswithspring.apiPayload.exception.GeneralException;
import com.example.awswithspring.domain.entity.UserEntity;
import com.example.awswithspring.dto.common.CommonPageReq;
import com.example.awswithspring.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUser(Long id){

        return userRepository.findById(id)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
    }

    public Page<UserEntity> searchUser(CommonPageReq pageRequest, String role){

        Pageable pageable = pageRequest.toPageable();

        Page<UserEntity> users = userRepository.findByRole(role, pageable);

        if (users.isEmpty()) {
            throw new GeneralException(ErrorStatus.MEMBER_NOT_FOUND);
        }

        return users;
    }
}
