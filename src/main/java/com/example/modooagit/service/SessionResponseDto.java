package com.example.modooagit.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionResponseDto {
    private String accessToken;

}
