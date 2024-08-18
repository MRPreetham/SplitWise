package org.example.splitwise.Commands;

import org.example.splitwise.Controllers.UserController;
import org.example.splitwise.Dtos.RegisterUserRequestDto;
import org.example.splitwise.Exceptions.UserExistException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class RegisterUserCommand implements Command{
    private final UserController userController;
    private final RegisterUserRequestDto requestDto;

    public RegisterUserCommand(UserController userController,
                               RegisterUserRequestDto requestDto){
        this.userController = userController;
        this.requestDto = requestDto;
    }
    @Override
    public boolean validate(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        return inputWords.size() == 4 && inputWords.get(0).equalsIgnoreCase(CommandKeyWords.REGISTER_USER);
    }

    @Override
    public void execute(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        requestDto.setUserName(inputWords.get(1));
        requestDto.setPhoneNumber(inputWords.get(2));
        requestDto.setPassword(inputWords.get(3));
        try {
            userController.registerUser(requestDto);
            System.out.println("User_created_successfully");
        }catch (UserExistException e) {
            System.out.println(e.getMessage());
        }
    }
}
