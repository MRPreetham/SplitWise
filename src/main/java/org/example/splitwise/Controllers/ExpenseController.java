package org.example.splitwise.Controllers;

import org.example.splitwise.Dtos.SettleUpGroupRequestDto;
import org.example.splitwise.Dtos.SettleUpGroupResponseDto;
import org.example.splitwise.Dtos.SettleUpUserRequestDto;
import org.example.splitwise.Dtos.SettleUpUserResponseDto;
import org.example.splitwise.Exceptions.GroupDoesNotExistException;
import org.example.splitwise.Exceptions.UserDoseNotExistException;
import org.example.splitwise.Services.ExpenseService;
import org.example.splitwise.Strategies.SettleUpStrategy.Transactions;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ExpenseController {
    private final ExpenseService expenseService;
    private final SettleUpUserResponseDto userResponseDto;
    private final SettleUpGroupResponseDto groupResponseDto;

    public ExpenseController(ExpenseService expenseService,
                             SettleUpUserResponseDto userResponseDto,
                             SettleUpGroupResponseDto groupResponseDto){
        this.expenseService = expenseService;
        this.userResponseDto = userResponseDto;
        this.groupResponseDto  = groupResponseDto;
    }
    public SettleUpUserResponseDto settleUpUser(SettleUpUserRequestDto requestDto) throws UserDoseNotExistException {
        List<Transactions> transactionsList = expenseService.settleUpUser(requestDto.getUserId());
        userResponseDto.setTransactionsList(transactionsList);
        return userResponseDto;
    }

    public SettleUpGroupResponseDto settleUpGroup(SettleUpGroupRequestDto requestDto) throws GroupDoesNotExistException {
        List<Transactions> transactionsList = expenseService.settleUpGroup(requestDto.getGroupId());
        groupResponseDto.setTransactionsList(transactionsList);
        return groupResponseDto;
    }
}
