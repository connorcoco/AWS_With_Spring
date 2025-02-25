package com.example.awswithspring.controller;

import com.example.awswithspring.apiPayload.ApiResponse;
import com.example.awswithspring.converter.UserConverter;
import com.example.awswithspring.domain.entity.UserEntity;
import com.example.awswithspring.dto.UserDTO.UserResponseDTO;
import com.example.awswithspring.dto.common.CommonPageReq;
import com.example.awswithspring.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Validated
@Tag(name = "User API", description = "User에 대한 API")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "유저 조회",
            description = "id로 유저 조회합니다.",
            parameters = {
                    @Parameter(
                            name = "id",
                            in = ParameterIn.PATH,
                            description = "유저 id"
                    )
            }
    )
    @GetMapping("/{id}")
    public ApiResponse<UserResponseDTO.GetUserRes> getUser(@PathVariable("id") Long id){

        UserEntity user = userService.getUser(id);

        return ApiResponse.onSuccess(UserConverter.toGetUserRes(user));
    }

    @Operation(
            summary = "유저 검색",
            description = "유저 리스트를 검색합니다.",
            parameters = {
                    @Parameter(
                            name = "limit",
                            in = ParameterIn.QUERY,
                            description = "항목 수 (기본값 10, 최소 1)",
                            example = "10"
                    ),
                    @Parameter(
                            name = "page",
                            in = ParameterIn.QUERY,
                            description = "페이지 번호 (기본값 0)",
                            example = "0"
                    ),
                    @Parameter(
                            name = "role",
                            in = ParameterIn.QUERY,
                            description = "유저 역할 (필수)",
                            required = true,
                            example = "ADMIN"
                    )
            }
    )
    @GetMapping("/")
    public ApiResponse<UserResponseDTO.SearchUserRes> searchUser(
            @ModelAttribute @Valid CommonPageReq pageRequest,
            @RequestParam String role
    ) {

        Page<UserEntity> userList = userService.searchUser(pageRequest, role);

        return ApiResponse.onSuccess(UserConverter.toSearchUserRes(userList));
    }
}
