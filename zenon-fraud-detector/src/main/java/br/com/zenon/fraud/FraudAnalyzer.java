package br.com.zenon.fraud;

import java.util.List;

public class FraudAnalyzer {
    
    private final List<Transaction> transactions;

    public FraudAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    
    public List<Transaction> getListOfFrauds() {
        return this.transactions.stream().filter(t -> t.isFraud()).toList();
    }
}
