package org.example.splitwise.Dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.splitwise.Strategies.SettleUpStrategy.Transactions;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
public class SettleUpGroupResponseDto {
    private List<Transactions> transactionsList;
    private String message;
    private String status;
}
