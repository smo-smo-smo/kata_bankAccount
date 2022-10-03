package com.bank.bankAccount.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Setter
@Getter
@SuperBuilder
public abstract class Transaction {

    String operation;
    LocalDateTime date;
    double amount;
    BankAccount bankAccount;
}
