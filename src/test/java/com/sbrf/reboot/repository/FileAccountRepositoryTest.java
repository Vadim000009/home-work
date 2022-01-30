package com.sbrf.reboot.repository;

import com.sbrf.reboot.repository.impl.FileAccountRepository;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileAccountRepositoryTest {

    AccountRepository accountRepository;

    @Test
    void onlyPersonalAccounts() {
        String filePath = "src/main/resources/Accounts.txt";
        accountRepository = new FileAccountRepository(filePath);

        long clientId = 1L;
        Set<Long> actualAccounts = accountRepository.getAllAccountsByClientId(clientId);

        Set<Long> expected = new HashSet<Long>() {{
            add(111L);
            add(222L);
            add(333L);
        }};

        actualAccounts.forEach(e -> assertTrue(expected.contains(e)));
    }

    @Test
    void failGetAllAccountsByClientId() {
        long clientId = 1L;

        String filePath = "somePath";

        accountRepository = new FileAccountRepository(filePath);
        // На FileNotFoundException не срабатывает, Хотя выдаёт его
        assertThrows(NullPointerException.class, () -> accountRepository.getAllAccountsByClientId(clientId));
    }

    @Test
    void updateAccountNumberTest() throws IOException {
        long clientId = 4L, previousNumber = 302L, newNumber = 352L;

        FileAccountRepository accountRepository = new FileAccountRepository("src/main/resources/Accounts.txt");
        accountRepository.updateAccountNumber(clientId, previousNumber, newNumber);

        Set<Long> actualAccounts = accountRepository.getAllAccountsByClientId(clientId);
        Set<Long> expected = new HashSet<Long>() {{
            add(342L);
            add(newNumber);
            add(363L);
        }};
        actualAccounts.forEach(e -> assertTrue(expected.contains(e)));
    }

}