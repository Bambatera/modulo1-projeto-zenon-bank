package br.com.zenon.fraud;

import br.com.zenon.fraud.enums.TransactionType;

public record Transaction(
        int step, 
        TransactionType type, 
        double amount, 
        Customer customerOrig, 
        Customer customizerDest, 
        boolean isFraud, 
        boolean isFlaggedFraud) {}
