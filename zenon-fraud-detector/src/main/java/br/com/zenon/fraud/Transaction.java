package br.com.zenon.fraud;

import br.com.zenon.fraud.enums.TransactionType;
import java.math.BigDecimal;

public record Transaction(
        int step, 
        TransactionType type, 
        BigDecimal amount, 
        Customer customerOrig, 
        Customer customizerDest, 
        boolean isFraud, 
        boolean isFlaggedFraud) {}
