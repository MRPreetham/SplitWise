package org.example.splitwise.Strategies.SettleUpStrategy;

import org.antlr.v4.runtime.misc.Pair;
import org.example.splitwise.Enums.UserExpenseType;
import org.example.splitwise.Models.Expense;
import org.example.splitwise.Models.User;
import org.example.splitwise.Models.UserExpense;
import org.example.splitwise.Repositories.UserExpenseRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.*;
@Primary
@Component("OptimalSettleUpStrategy")
public class OptimalSettleUpStrategy implements SettleUpStrategy{

    private final UserExpenseRepository userExpenseRepository;

    public OptimalSettleUpStrategy(UserExpenseRepository userExpenseRepository){
        this.userExpenseRepository = userExpenseRepository;
    }
    @Override
    public List<Transactions> settleUp(List<Expense> expenseList) {
        List<UserExpense> userExpenseList = userExpenseRepository.findAllByExpenseIn(expenseList);
        HashMap<User,Integer> userExpenseHashMap = new HashMap<>();
        for(UserExpense userExpense : userExpenseList){
            User user = userExpense.getUser();
            Integer amount = 0;
            if(userExpenseHashMap.containsKey(user)){
                amount = userExpenseHashMap.get(user);
            }
            if(userExpense.getUserExpenseType().equals(UserExpenseType.AMOUNT_PAID)){
                userExpenseHashMap.put(user,amount+userExpense.getAmount());
            }else{
                userExpenseHashMap.put(user,amount-userExpense.getAmount());
            }
        }
        TreeSet<Pair<User,Integer>> paidUser = new TreeSet<>();
        TreeSet<Pair<User,Integer>> owedUser = new TreeSet<>();
        for(Map.Entry<User,Integer> entry : userExpenseHashMap.entrySet()){
            if(entry.getValue()>0){
                paidUser.add(new Pair<>(entry.getKey(),entry.getValue()));
            }else{
                owedUser.add(new Pair<>(entry.getKey(),entry.getValue()));
            }
        }
        List<Transactions> transactionsList = new ArrayList<>();
        while(!paidUser.isEmpty() && !owedUser.isEmpty()){
            Transactions t = new Transactions();
            Pair<User,Integer> extraPaidUser = paidUser.pollFirst();
            Pair<User,Integer> lessPaidUser = owedUser.pollFirst();
            if(Math.abs(extraPaidUser.b)> Math.abs(lessPaidUser.b)){
                paidUser.add(new Pair<>(extraPaidUser.a, extraPaidUser.b - lessPaidUser.b));
                t.setAmount(Math.abs(lessPaidUser.b));
            }else if(Math.abs(extraPaidUser.b)< Math.abs(lessPaidUser.b)){
                owedUser.add(new Pair<>(lessPaidUser.a, lessPaidUser.b + extraPaidUser.b));
                t.setAmount(Math.abs(extraPaidUser.b));
            }else{
                t.setAmount(Math.abs(lessPaidUser.b));
            }
            t.setFromUser(lessPaidUser.a.getUserName());
            t.setToUser(extraPaidUser.a.getUserName());
            transactionsList.add(t);
        }

        return transactionsList;
    }
}
