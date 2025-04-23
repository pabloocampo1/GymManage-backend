package com.GymManager.Backend.domain.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private String username;
    private String email;
}
