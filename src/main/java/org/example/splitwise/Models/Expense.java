package org.example.splitwise.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.example.splitwise.Enums.ExpenseType;

@Getter
@Setter
@Entity
public class Expense extends  BaseModel{
    private String description;
    private Integer amount;
    @ManyToOne
    private User createdBy;
    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;
    private Long groupId;
}
