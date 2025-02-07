package com.example.awswithspring.controller;

import com.example.awswithspring.apiPayload.ApiResponse;
import com.example.awswithspring.converter.UserConverter;
import com.example.awswithspring.domain.entity.UserEntity;
import com.example.awswithspring.dto.AuthDTO.AuthRequestDTO;
import com.example.awswithspring.dto.AuthDTO.AuthResponseDTO;
import com.example.awswithspring.service.JoinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@Tag(name = "Auth API", description = "Auth에 대한 API")
@RequestMapping("/auth")
public class AuthController {

    private final JoinService joinService;

    public AuthController(JoinService joinService) {
        this.joinService = joinService;
    }

    @Operation(summary = "회원가입", description = "Post (username, password, nickname, gender)")
    @PostMapping("/join")
    public ApiResponse<AuthResponseDTO.JoinResultDTO> joinProcess(@RequestBody @Valid AuthRequestDTO.JoinDTO request){

        UserEntity newUser = joinService.joinProcess(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(newUser));
    }
}
