package org.example.splitwise.ControllerAdvices;

import org.example.splitwise.Exceptions.GroupDoesNotExistException;
import org.example.splitwise.Exceptions.UserDoseNotExistException;
import org.example.splitwise.Exceptions.UserExistException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class ExceptionHandlers {

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<UserExistException> UserExistException(UserExistException e){
        return new ResponseEntity<>(e,HttpStatusCode.valueOf(402));
    }

    @ExceptionHandler(UserDoseNotExistException.class)
    public ResponseEntity<UserDoseNotExistException> UserDoesNotExistException(UserDoseNotExistException e){
        return new ResponseEntity<>(e,HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(GroupDoesNotExistException.class)
    public ResponseEntity<GroupDoesNotExistException> GroupDoesNotExistException(GroupDoesNotExistException e){
        return new ResponseEntity<>(e,HttpStatusCode.valueOf(404));
    }
}
