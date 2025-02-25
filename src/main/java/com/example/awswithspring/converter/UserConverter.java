package com.example.awswithspring.converter;

import com.example.awswithspring.domain.entity.UserEntity;
import com.example.awswithspring.domain.entity.enums.Gender;
import com.example.awswithspring.dto.AuthDTO.AuthRequestDTO;
import com.example.awswithspring.dto.AuthDTO.AuthResponseDTO;
import com.example.awswithspring.dto.UserDTO.UserResponseDTO;
import com.example.awswithspring.dto.common.CommonPageRes;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    // 날짜를 포맷하는 메서드
    private static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    public static AuthResponseDTO.JoinResultDTO toJoinResultDTO(UserEntity user){
        return AuthResponseDTO.JoinResultDTO.builder()
                .memberId(user.getId())
                .createAt(formatDateTime(user.getCreatedAt()))
                .build();
    }

    //    UserEntity 객체를 만드는 작업 (클라이언트가 준 DTO to Entity)
    public static UserEntity toUser(AuthRequestDTO.JoinDTO request, BCryptPasswordEncoder bCryptPasswordEncoder){

        return UserEntity.builder()
                .username(request.getUsername())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
//                .role("ROLE_USER")
//                .accountStatus(AccountStatus.ACTIVE)
                .nickname(request.getNickname())
                .gender(Gender.valueOf(request.getGender()))
                .build();
    }

    public static UserResponseDTO.GetUserRes toGetUserRes(UserEntity user){
        return UserResponseDTO.GetUserRes.builder()
                .username(user.getUsername())
                .role(user.getRole())
                .accountStatus(user.getAccountStatus())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .build();
    }

    public static UserResponseDTO.SearchUserRes toSearchUserRes(Page<UserEntity> userList){

        List<UserResponseDTO.GetUserRes> getUserResList = userList.stream()
                .map(UserConverter::toGetUserRes).collect(Collectors.toList());

        CommonPageRes commonPageRes = new CommonPageRes(
                userList.getTotalElements(),   // 총 개수 (count)
                userList.getSize(),           // 페이지 당 개수 (limit)
                userList.getNumber()          // 현재 페이지 번호 (page)
        );

        return UserResponseDTO.SearchUserRes.builder()
                .getUserResList(getUserResList)
                .count(commonPageRes.getCount())
                .limit(commonPageRes.getLimit())
                .page(commonPageRes.getPage())
                .build();
    }
}
