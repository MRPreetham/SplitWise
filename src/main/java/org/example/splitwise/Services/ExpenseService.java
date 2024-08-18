package org.example.splitwise.Services;

import org.example.splitwise.Exceptions.GroupDoesNotExistException;
import org.example.splitwise.Exceptions.UserDoseNotExistException;
import org.example.splitwise.Models.Expense;
import org.example.splitwise.Models.UserExpense;
import org.example.splitwise.Repositories.ExpenseRepository;
import org.example.splitwise.Repositories.GroupRepository;
import org.example.splitwise.Repositories.UserExpenseRepository;
import org.example.splitwise.Repositories.UserRepository;
import org.example.splitwise.Strategies.SettleUpStrategy.SettleUpStrategy;
import org.example.splitwise.Strategies.SettleUpStrategy.Transactions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserExpenseRepository userExpenseRepository;
    private final ExpenseRepository expenseRepository;
    private final SettleUpStrategy settleUpStrategy;

    public ExpenseService(UserExpenseRepository userExpenseRepository,
                          @Qualifier("OptimalSettleUpStrategy") SettleUpStrategy settleUpStrategy,
                          ExpenseRepository expenseRepository,
                          UserRepository userRepository,
                          GroupRepository groupRepository){
        this.userExpenseRepository = userExpenseRepository;
        this.settleUpStrategy = settleUpStrategy;
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public List<Transactions> settleUpUser(Long userId) throws UserDoseNotExistException {
        if(!userRepository.existsUserById(userId)){
            throw new UserDoseNotExistException(
                    "user with Id "+userId+" doesn't exist"
            );
        }
        List<UserExpense> userExpenseList = userExpenseRepository.findAllByUserId(userId);
        List<Expense> expenseList = new ArrayList<>();
        for(UserExpense userExpense : userExpenseList){
            expenseList.add(userExpense.getExpense());
        }
       return settleUpStrategy.settleUp(expenseList);
    }

    public List<Transactions> settleUpGroup(Long groupId) throws GroupDoesNotExistException {
        if(!groupRepository.existsGroupById(groupId)){
           throw new GroupDoesNotExistException(
                   "Group with Id "+groupId+" doesn't exist"
           );
        }
        List<Expense> expenseList = expenseRepository.findAllByGroupId(groupId);
        return settleUpStrategy.settleUp(expenseList);
    }
}
