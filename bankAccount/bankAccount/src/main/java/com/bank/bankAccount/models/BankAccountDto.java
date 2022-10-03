package com.bank.bankAccount.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
public class BankAccountDto {
    long id;
    double balance;
    List<TransactionDto> transactions;

    public List<TransactionDto> getTransactions() {
        if(transactions == null) return new ArrayList<>();
        return transactions;
    }

}
