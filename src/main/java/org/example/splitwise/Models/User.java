package org.example.splitwise.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.example.splitwise.Enums.UserStatus;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String userName;
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    @ManyToMany
    private List<Group> groupList;
}
