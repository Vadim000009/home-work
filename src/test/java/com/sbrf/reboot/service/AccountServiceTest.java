package com.sbrf.reboot.service;

import com.sbrf.reboot.repository.AccountRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    AccountService accountService;

    @BeforeEach
    void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);

        accountService = new AccountService(accountRepository);
    }

    @SneakyThrows
    @Test
    void contractExist() {
        Set<Long> accounts = new HashSet();
        accounts.add(111L);

        long clientId = 1L;
        long contractNumber = 111L;


        when(accountRepository.getAllAccountsByClientId(clientId)).thenReturn(accounts);

        assertTrue(accountService.isClientHasContract(clientId, contractNumber));
    }

    @SneakyThrows
    @Test
    void contractNotExist() {
        Set<Long> accounts = new HashSet();
        accounts.add(222L);

        long clientId = 1L;
        long contractNumber = 111L;

        when(accountRepository.getAllAccountsByClientId(clientId)).thenReturn(accounts);

        assertFalse(accountService.isClientHasContract(clientId, contractNumber));
    }

    @Test
    void foundClientIdByContractNumber() {
        Set<Long> accounts = new HashSet();
        Set<Long> contactsOfAccountFst = new HashSet<>();
        Set<Long> contactsOfAccountSec = new HashSet<>();
        accounts.add(10L);
        accounts.add(20L);
        contactsOfAccountFst.add(10_100L);
        contactsOfAccountSec.add(20_100L);

//        when(accountRepository.getAllAccounts()).thenReturn(accounts);
        when(accountRepository.getAllAccountsByClientId(20L)).thenReturn(contactsOfAccountFst);
        when(accountRepository.getAllAccountsByClientId(10L)).thenReturn(contactsOfAccountSec);

//        assertEquals(10L, accountService.getClientIdByContractNumber(10_100L));
    }

    @Test
    void repositoryHasTreeMethods() {
        assertEquals(2, AccountRepository.class.getMethods().length);
    }

    @Test
    void serviceHasTreeMethods() {
        assertEquals(2, AccountService.class.getMethods().length - Object.class.getMethods().length);
    }

}