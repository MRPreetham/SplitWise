package org.example.splitwise.Strategies.SettleUpStrategy;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
@Getter
@Setter
@Component
public class Transactions {
    private String fromUser;
    private String toUser;
    private Integer amount;
}
