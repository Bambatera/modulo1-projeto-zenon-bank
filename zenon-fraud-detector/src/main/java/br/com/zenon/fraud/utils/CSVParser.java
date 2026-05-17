package br.com.zenon.fraud.utils;

import br.com.zenon.fraud.Customer;
import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.enums.TransactionType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CSVParser {

    public static List<Transaction> contentParser(List<String> fileContent) {
        List<Transaction> transactions = new ArrayList<>();
        int errorCount = 0;
        for (String cnt : fileContent) {
            String[] lines = cnt.split("\n");
            for (String line : lines) {
                String[] contentLine = line.split(",");

                try {
                    int step = getStepValue(contentLine[0]);
                    TransactionType type = getTypeValue(contentLine[1]);
                    double amount = 0;
                    try {
                        amount = getDoubleContentValue(contentLine[2]);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("'amount' " + e.getMessage());
                    }

                    String nameOrig = Optional.ofNullable(contentLine[3])
                            .orElseThrow(() -> new IllegalArgumentException("'nameOrig' must have a value!"));
                    if (nameOrig.trim().isEmpty()) {
                        throw new IllegalArgumentException("'nameOrig' must have a value!");
                    }

                    double oldBalanceOrig = 0;
                    try {
                        oldBalanceOrig = getDoubleContentValue(contentLine[4]);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("'oldBalanceOrig' " + e.getMessage());
                    }

                    double newBalanceOrig = 0;
                    try {
                        newBalanceOrig = getDoubleContentValue(contentLine[5]);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("'newBalanceOrig' " + e.getMessage());
                    }

                    Customer originCustomer = new Customer(nameOrig, oldBalanceOrig, newBalanceOrig);

                    String nameDest = Optional.ofNullable(contentLine[6])
                            .orElseThrow(() -> new IllegalArgumentException("'nameDest' must have a value!"));
                    if (nameDest.trim().isEmpty()) {
                        throw new IllegalArgumentException("'nameDest' must have a value!");
                    }

                    double oldBalanceDest = 0;
                    try {
                        oldBalanceDest = getDoubleContentValue(contentLine[7]);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("'oldBalanceDest' " + e.getMessage());
                    }

                    double newBalanceDest = 0;
                    try {
                        newBalanceDest = getDoubleContentValue(contentLine[8]);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("'newBalanceDest' " + e.getMessage());
                    }

                    Customer destinationCustomer = new Customer(nameDest, oldBalanceDest, newBalanceDest);

                    boolean isFraud = getBooleanValue(contentLine[9]);
                    boolean isFlaggedFraud = getBooleanValue(contentLine[10]);

                    Transaction transaction = new Transaction(step, type, amount, originCustomer, destinationCustomer, isFraud, isFlaggedFraud);
                    transactions.add(transaction);
                } catch (NumberFormatException ex) {
                    System.err.println("Error: " + line + "|" + ex.getClass().getName() + ": " + ex.getMessage());
                    errorCount++;
                } catch (IllegalArgumentException ex) {
                    System.err.println("Error: " + line + "|" + ex.getClass().getName() + ": " + ex.getMessage());
                    errorCount++;
                }
            }
        }
        if (errorCount > 0) {
            System.err.println(errorCount);
        }
        return transactions;
    }

    /**
     * Obtém o valor do <i>Step</i> e realiza as validações necessárias para o
     * preenchimento correto da variável.
     *
     * @param content Conteúdo a ser verificado e obtido o valor.
     * @return Valor do <i>Step</i>.
     * @throws IllegalArgumentException Será retornado caso alguma
     * inconsistência seja detectada.
     */
    private static int getStepValue(String content) throws IllegalArgumentException {
        int step;
        String stepValue = Optional.ofNullable(content)
                .orElseThrow(() -> new IllegalArgumentException("'step' must have a value!"));
        if (stepValue.trim().isEmpty()) {
            throw new IllegalArgumentException("'step' must have a value!");
        }
        try {
            step = Integer.parseInt(content);
            if (step <= 0) {
                throw new IllegalArgumentException("'step' must be a positive number gratter then 0 (zero)!");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("'step' must be a number: " + stepValue);
        }
        return step;
    }

    /**
     * Obtém o valor do <i>Type</i> e realiza as validações necessárias para o
     * preenchimento correto da variável.
     *
     * @param content Conteúdo a ser verificado e obtido o valor.
     * @return Valor de <i>Type</i>
     * @throws IllegalArgumentException Será retornado caso alguma
     * inconsistência seja detectada.
     */
    private static TransactionType getTypeValue(String content) throws IllegalArgumentException {
        TransactionType type = null;
        String typeValue = Optional.ofNullable(content)
                .orElseThrow(() -> new IllegalArgumentException("'type' must have a value!"));
        if (typeValue.trim().isEmpty()) {
            throw new IllegalArgumentException("'type' must have a value!");
        }
        try {
            type = TransactionType.valueOf(typeValue);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("'type' must have a valid option: " + e.getMessage());
        }
        return type;
    }

    /**
     * Obtém o valor <i>double</i> do conteúdo informado e realiza as validações
     * necessárias para o preenchimento correto da variável.
     *
     * @param content Conteúdo a ser verificado e obtido o valor.
     * @return Valor <i>double</i> do conteúdo.
     * @throws IllegalArgumentException Será retornado caso alguma
     * inconsistência seja detectada.
     */
    private static double getDoubleContentValue(String content) throws IllegalArgumentException {
        double dblContent;
        String contentValue = Optional.ofNullable(content)
                .orElseThrow(() -> new IllegalArgumentException("must have a value!"));
        try {
            dblContent = Double.parseDouble(contentValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("must be a number: " + contentValue);
        }
        if (dblContent < 0) {
            throw new IllegalArgumentException(" must be a positive number: " + dblContent);
        }
        return dblContent;
    }

    /**
     * Obtém o valor booleano do conteúdo informado no parâmetro.
     *
     * @param content String contendo o valor a ser obtido, <em>null</em> pode
     * ser tratado.
     * @return caso o conteúdo possua algum valor, este será verificado e
     * comparado a <em>"1"</em> e retornará <em>true</em>, caso contrário,
     * <em>false</em> será retornado (este será o valor padrão retornado).
     */
    private static boolean getBooleanValue(String content) {
        boolean blnValue = false;

        if (Optional.ofNullable(content).isPresent()) {
            blnValue = content.trim().equals("1");
        }

        return blnValue;
    }
}
