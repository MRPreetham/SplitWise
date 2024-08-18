package org.example.splitwise.Strategies.SettleUpStrategy;

import org.example.splitwise.Models.Expense;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface SettleUpStrategy {
    List<Transactions> settleUp(List<Expense> expenseList);
}
