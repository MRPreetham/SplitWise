package org.example.splitwise.Commands;

import org.example.splitwise.Controllers.ExpenseController;
import org.example.splitwise.Dtos.SettleUpUserRequestDto;
import org.example.splitwise.Dtos.SettleUpUserResponseDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class SettleUpUserCommand implements Command{
    //u1 SettleUp
    private final ExpenseController expenseController;
    private final SettleUpUserRequestDto userRequestDto;

    public SettleUpUserCommand(ExpenseController expenseController,
                               SettleUpUserRequestDto userRequestDto){
        this.expenseController = expenseController;
        this.userRequestDto = userRequestDto;
    }
    @Override
    public boolean validate(String input) {
        List<String> inWords = Arrays.stream(input.split(" ")).toList();
        return inWords.size() == 2 && inWords.get(1).equalsIgnoreCase(CommandKeyWords.SETTLE_UP);
    }

    @Override
    public void execute(String input) {
        List<String> inWords = Arrays.stream(input.split(" ")).toList();
        userRequestDto.setUserId(Long.parseLong(inWords.get(0)));
        try{
            SettleUpUserResponseDto responseDto =  expenseController.settleUpUser(userRequestDto);
            System.out.println("SettledUp user with Id "+ inWords.get(1));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
