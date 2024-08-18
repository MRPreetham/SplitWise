package org.example.splitwise.Controllers;

import org.example.splitwise.Dtos.RegisterUserRequestDto;
import org.example.splitwise.Dtos.RegisterUserResponseDto;
import org.example.splitwise.Exceptions.UserExistException;
import org.example.splitwise.Models.User;
import org.example.splitwise.Services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private RegisterUserRequestDto registerUserRequestDto;
    private UserService userService;
    private RegisterUserResponseDto registerUserResponseDto;

    public UserController(RegisterUserRequestDto registerUserRequestDto,
                          UserService userService,
                          RegisterUserResponseDto registerUserResponseDto){
        this.registerUserRequestDto = registerUserRequestDto;
        this.userService = userService;
        this.registerUserResponseDto = registerUserResponseDto;
    }

    public RegisterUserResponseDto registerUser(RegisterUserRequestDto requestDto) throws UserExistException {
        String userName = requestDto.getUserName();
        String phoneNumber = requestDto.getPhoneNumber();
        String password = requestDto.getPassword();
        User user = userService.registerUser(userName,password,phoneNumber);
        registerUserResponseDto.setUserId(user.getId());
        return registerUserResponseDto;
    }
}
