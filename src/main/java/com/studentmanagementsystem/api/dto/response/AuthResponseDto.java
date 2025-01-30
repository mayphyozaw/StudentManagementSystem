package com.studentmanagementsystem.api.dto.response;

import lombok.Data;

@Data
public class AuthResponseDto {

    private String accessToken;
    private String tokenType;

    public AuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
