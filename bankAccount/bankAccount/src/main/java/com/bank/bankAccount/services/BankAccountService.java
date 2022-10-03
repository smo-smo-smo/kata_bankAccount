package com.bank.bankAccount.services;

import com.bank.bankAccount.entities.BankAccount;
import com.bank.bankAccount.entities.Deposit;
import com.bank.bankAccount.entities.Transaction;
import com.bank.bankAccount.entities.Withdrawal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BankAccountService {

    public List<BankAccount> bankAccounts = new ArrayList<>();

    public BankAccount getBankAccount(long idAccount) throws Exception {
        return  bankAccounts.stream().filter(t -> t.getId() == idAccount).findFirst().orElseThrow(()-> new Exception("Bank account not fount"));
    }

    public BankAccount deposit(long idAccount, double amount) throws Exception {
        BankAccount bankAccount = getBankAccount(idAccount);
        isPositifAmount(amount);
        double newBalance =  Double.sum(bankAccount.getBalance(), amount);
        return bankAccounts.stream()
                            .filter(ba->ba.equals(bankAccount))
                            .peek(b->{b.setBalance(newBalance);addDeposit(bankAccount,"deposit",amount);})
                            .findFirst()
                            .get();
    }

    public BankAccount withdrawal(long idAccount, double amount)throws Exception {
        BankAccount bankAccount = getBankAccount(idAccount);
        isPositifAmount(amount);
        double newBalance = calculateNewBalance(bankAccount,amount);
        addWWithdrawal(bankAccount,"withdrawal",amount);
        return bankAccounts.stream().filter(ba->ba.equals(bankAccount)).peek(b->b.setBalance(newBalance)).findFirst().get();
    }

    public boolean isPositifAmount(double amount) throws Exception{
        if(amount<0) throw new Exception("amount must be superior than 0");
        return true;
    }

    public double calculateNewBalance(BankAccount bankAccount,double amount) throws Exception{
        double newBalance =  Double.sum(bankAccount.getBalance(), -amount);
        if(newBalance< 0) throw new Exception("Balance is not sufficient to perform this operation");
        return newBalance;
    }

    public boolean addDeposit(BankAccount bankAccount, String operation, double amount){
        return bankAccount.getTransactions()
                          .add(Deposit.builder()
                                      .operation(operation)
                                      .bankAccount(bankAccount)
                                      .date(LocalDateTime.now())
                                      .amount(amount)
                                      .build()
                          );
    }

    public boolean addWWithdrawal(BankAccount bankAccount, String operation, double amount){
        return bankAccount.getTransactions()
                          .add(Withdrawal.builder()
                                         .operation(operation)
                                         .bankAccount(bankAccount)
                                         .date(LocalDateTime.now())
                                         .amount(amount)
                                         .build()
                          );
    }
}