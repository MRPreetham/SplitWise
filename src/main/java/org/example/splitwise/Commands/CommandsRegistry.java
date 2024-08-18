package org.example.splitwise.Commands;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CommandsRegistry {
    private List<Command> commandList;

    public CommandsRegistry(RegisterUserCommand registerUserCommand,
                            SettleUpUserCommand settleUpUserCommand,
                            SettleUpGroupCommand settleUpGroupCommand){
        commandList = new ArrayList<>();
        commandList.add(registerUserCommand);
        commandList.add(settleUpUserCommand);
        commandList.add(settleUpGroupCommand);
    }

    public void execute(String input){
        for(Command command : commandList){
            if(command.validate(input)){
                command.execute(input);
                break;
            }
        }
    }
}
