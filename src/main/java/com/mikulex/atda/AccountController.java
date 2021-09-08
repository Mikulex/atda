package com.mikulex.atda;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private AccountRepository accountRepository;
    private AccountModelAssembler accountAssembler;

    public AccountController(AccountRepository accountRepository, AccountModelAssembler accountAssembler){
        
        this.accountAssembler = accountAssembler;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/accounts/{id}")
    EntityModel<Account> one(@PathVariable Long id){
        
        Account account = accountRepository.findById(id).orElseThrow();
        return accountAssembler.toModel(account);
    }

}
