package br.com.zenon.fraud.utils;

import br.com.zenon.fraud.Customer;
import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.enums.TransactionType;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    public static List<Transaction> contentParser(List<String> fileContent) {
        List<Transaction> transactions = new ArrayList<>();
        for (String cnt : fileContent) {
            String[] lines = cnt.split("\n");
            for (String line : lines) {
                String[] contentLine = line.split(",");
                Transaction transaction = new Transaction(
                Integer.parseInt(contentLine[0]),
                TransactionType.valueOf(contentLine[1]),
                Double.parseDouble(contentLine[2]),
                new Customer(contentLine[3], Double.parseDouble(contentLine[4]), Double.parseDouble(contentLine[5])),
                new Customer(contentLine[6], Double.parseDouble(contentLine[7]), Double.parseDouble(contentLine[8])),
                Integer.parseInt(contentLine[9]) == 1 ? true : false,
                Integer.parseInt(contentLine[10]) == 1 ? true : false);
                transactions.add(transaction);
            }
        }
        return transactions;
    }
}
