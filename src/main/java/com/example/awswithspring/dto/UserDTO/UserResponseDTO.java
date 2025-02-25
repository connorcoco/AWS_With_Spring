package com.example.awswithspring.dto.UserDTO;

import com.example.awswithspring.domain.entity.enums.AccountStatus;
import com.example.awswithspring.domain.entity.enums.Gender;
import com.example.awswithspring.dto.common.CommonPageRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

public class UserResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetUserRes{

        private String username;
        private String role;
        private AccountStatus accountStatus;
        private String nickname;
        private Gender gender;
    }

    @SuperBuilder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchUserRes extends CommonPageRes {

        private List<GetUserRes> getUserResList;
    }
}
