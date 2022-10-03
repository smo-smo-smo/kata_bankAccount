package com.bank.bankAccount;

import com.bank.bankAccount.entities.BankAccount;
import com.bank.bankAccount.services.BankAccountService;
import static junit.framework.Assert.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class BankAccountServiceTest {

    BankAccountService bankAccountService;
    private List<BankAccount> bankAccounts;

    @BeforeEach
    public void init() throws Exception {
        BankAccount bankAccount = BankAccount.builder().id(1).balance(100).build();
        BankAccount bankAccount2 = BankAccount.builder().id(2).balance(200).build();
        bankAccounts = new ArrayList<>(Arrays.asList(bankAccount,bankAccount2));
        bankAccountService.bankAccounts = bankAccounts ;
    }

    @Autowired
    public BankAccountServiceTest(BankAccountService bankAccountService){
        this.bankAccountService = bankAccountService;
    }
    @Test
    public void whenGettingBankAccount_thenOK() throws Exception {
       BankAccount bankAccount = bankAccountService.getBankAccount(1);
       assertEquals(bankAccount,bankAccounts.get(0));
    }

    @Test
    public void whenGettingBankAccount_thenNOK() {
        Assertions.assertThrows(Exception.class, () -> {
            bankAccountService.withdrawal(1,-50);
        },"Bank account not fount");
    }

    @Test
    public void whenDepositPositif_thenOK() throws Exception {
        BankAccount bankAccount = bankAccountService.deposit(1, 50) ;
        assertEquals(bankAccount.getBalance(),150d);
    }

    @Test
    public void  whenWithdrawalPositif_thenNOK() throws Exception {
        BankAccount bankAccount = bankAccountService.withdrawal(1, 50) ;
        assertEquals(bankAccount.getBalance(),50d);
    }

    @Test
    public void  whenWithdrawalNegatif_thenNOK() {
        Assertions.assertThrows(Exception.class, () -> {
            bankAccountService.withdrawal(1,-50);
        },"amount must be superior than 0");
    }

    @Test
    public void  whenBalanceNotSufficient_thenNOK() {
        Assertions.assertThrows(Exception.class, () -> {
            bankAccountService.withdrawal(1,-50);
        },"Balance is not sufficient to perform this operation");
    }

    @Test
    public void withdrawalNok() throws Exception {
        Assertions.assertThrows(Exception.class, () -> {
            bankAccountService.withdrawal(1,-50);
        },"amount must be superior than 0");
    }

}
