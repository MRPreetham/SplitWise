package org.example.splitwise.Services;

import org.example.splitwise.Enums.UserStatus;
import org.example.splitwise.Exceptions.UserExistException;
import org.example.splitwise.Models.User;
import org.example.splitwise.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User registerUser(String userName,String password,String phoneNumber) throws UserExistException {
        Optional<User> userOptional = userRepository.findUserByPhoneNumber(phoneNumber);
        if(userOptional.isPresent()){
            if(userOptional.get().getUserStatus().equals(UserStatus.ACTIVE)){
                throw new UserExistException(
                        "User with phoneNumber "+phoneNumber+" exist"
                );
            }else{
                User user = userOptional.get();
                user.setUserName(userName);
                user.setPassword(password);
                user.setPhoneNumber(phoneNumber);
                user.setUserStatus(UserStatus.ACTIVE);
                return userRepository.save(user);
            }
        }
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setUserStatus(UserStatus.ACTIVE);
        return userRepository.save(user);
    }
}
