package com.bank.bankAccount.controllers;

import com.bank.bankAccount.entities.BankAccount;
import com.bank.bankAccount.entities.Transaction;
import com.bank.bankAccount.models.BankAccountDto;
import com.bank.bankAccount.models.TransactionDto;
import com.bank.bankAccount.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bankaccounts")
public class BankAccountController {

    ModelMapper modelMapper = new ModelMapper();
    public BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService){
       this.bankAccountService = bankAccountService;
    }

    @PutMapping("/{idAccount}")
    public BankAccountDto getBankAccount(@PathVariable long idAccount) throws Exception{
        BankAccount bankAccount = bankAccountService.getBankAccount(idAccount);
        return modelMapper.map(bankAccount, BankAccountDto.class);
    }

    @PutMapping("/{idAccount}/deposit")
    public BankAccountDto deposit(@PathVariable long idAccount,@RequestParam double amount)throws Exception{
        BankAccount bankAccount = bankAccountService.deposit(idAccount,amount);
        return modelMapper.map(bankAccount, BankAccountDto.class);
    }

    @PutMapping("/{idAccount}/withdrawal")
    public BankAccountDto withdrawal(@PathVariable long idAccount,@RequestParam double amount)throws Exception{
        BankAccount bankAccount = bankAccountService.withdrawal(idAccount,amount);
        return modelMapper.map(bankAccount, BankAccountDto.class);
    }
}
