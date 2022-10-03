package com.bank.bankAccount.models;

import com.bank.bankAccount.entities.BankAccount;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Setter
@Getter
@SuperBuilder
public abstract class TransactionDto{

    String operation;
    LocalDateTime date;
    double amount;
    long bankAccountDto;
}
