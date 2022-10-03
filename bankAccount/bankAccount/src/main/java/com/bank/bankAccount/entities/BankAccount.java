package com.bank.bankAccount.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class BankAccount {

    long id;
    double balance;
    List<Transaction> transactions ;

    public List<Transaction> getTransactions() {
        if(transactions == null) return new ArrayList<>();
        return transactions;
    }

}
