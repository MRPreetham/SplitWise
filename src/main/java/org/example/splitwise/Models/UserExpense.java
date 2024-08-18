package org.example.splitwise.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.example.splitwise.Enums.UserExpenseType;

@Getter
@Setter
@Entity
public class UserExpense extends  BaseModel{
    @ManyToOne
    private User user;
    @ManyToOne
    private Expense expense;
    private Integer amount;
    @Enumerated(EnumType.STRING)
    private UserExpenseType userExpenseType;
}
