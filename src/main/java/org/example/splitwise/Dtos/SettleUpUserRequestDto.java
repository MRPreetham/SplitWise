package org.example.splitwise.Dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class SettleUpUserRequestDto {
    private Long userId;
}
