package org.example.splitwise.Repositories;

import org.example.splitwise.Models.Expense;
import org.example.splitwise.Models.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserExpenseRepository extends JpaRepository<UserExpense,Long> {
    List<UserExpense> findAllByUserId (Long Id);
    List<UserExpense> findAllByExpenseIn (List<Expense> expenseList);
}
