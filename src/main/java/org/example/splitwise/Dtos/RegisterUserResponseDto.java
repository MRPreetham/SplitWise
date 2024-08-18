package org.example.splitwise.Dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class RegisterUserResponseDto {
    private Long userId;
    private String message;
    private String status;
}
