package org.example.splitwise.Commands;

import org.example.splitwise.Controllers.ExpenseController;
import org.example.splitwise.Dtos.SettleUpGroupRequestDto;
import org.example.splitwise.Dtos.SettleUpGroupResponseDto;
import org.example.splitwise.Dtos.SettleUpUserRequestDto;
import org.example.splitwise.Dtos.SettleUpUserResponseDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SettleUpGroupCommand implements Command{
    private final ExpenseController expenseController;
    private final SettleUpGroupRequestDto groupRequestDto;

    public SettleUpGroupCommand(ExpenseController expenseController,
                               SettleUpGroupRequestDto groupRequestDto){
        this.expenseController = expenseController;
        this.groupRequestDto = groupRequestDto;
    }
    @Override
    public boolean validate(String input) {
        List<String> inWords = Arrays.stream(input.split(" ")).toList();
        return inWords.size() == 3 && inWords.get(1).equalsIgnoreCase(CommandKeyWords.SETTLE_UP);
    }

    @Override
    public void execute(String input) {
        List<String> inWords = Arrays.stream(input.split(" ")).toList();
        groupRequestDto.setGroupId(Long.parseLong(inWords.get(2)));
        try{
            SettleUpGroupResponseDto responseDto =  expenseController.settleUpGroup(groupRequestDto);
            System.out.println("SettledUp Group with Id "+ inWords.get(2));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
