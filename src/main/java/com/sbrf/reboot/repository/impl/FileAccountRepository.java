package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.repository.AccountRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FileAccountRepository implements AccountRepository {

    private final String pathToRepository;

    public FileAccountRepository(String pathToRepository) {
        this.pathToRepository = pathToRepository;
    }

    @Override
    public Set<Long> getAllAccountsByClientId(long clientId) {
        String stringFile = null;
        try {
            stringFile = readFileToString(pathToRepository);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> clientsId = new ArrayList<>();
        List<String> numbers = new ArrayList<>();
        String[] array = stringFile.split("\n");

        for (String result : array) {
            if (result.contains("clientId"))
                clientsId.add(result.trim());
            if (result.contains("number"))
                numbers.add(result.trim());
        }
        return findNumbersByClientId(clientsId, numbers, clientId);
    }

    private Set<Long> findNumbersByClientId(List<String> clients, List<String> numbers, long targetClientId) {
        String client = String.format("\"clientId\": %d", targetClientId);
        Set<Long> numbersForClientId = new HashSet<>();
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).contains(client)) {
                long num = Long.parseLong(numbers.get(i).split(" ")[1]);
                numbersForClientId.add(num);
            }
        }
        return numbersForClientId;
    }

    private String readFileToString(String path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        while (bufferedReader.ready()) {
            stringBuilder.append(bufferedReader.readLine()).append("\n");
        }
        return stringBuilder.toString();
    }

    public void updateAccountNumber(Long clientId, Long previousNumber, Long newNumber) throws IOException {
        String strClient    = String.format("\"clientId\": %d,", clientId);
        String strNumber    = String.format("\"number\": %d", previousNumber);
        String strFind      = "\t" + strClient + "\n\t" + strNumber;
        String strReplace   = "\t" + strClient + "\n\t" + String.format("\"number\": %d", newNumber);

        String strData = readFileToString(pathToRepository);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File(pathToRepository), false);
            fileWriter.write(strData.replace(strFind, strReplace));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileWriter.flush();
            fileWriter.close();
        }
    }

}