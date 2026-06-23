package br.com.zenon.fraud;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author leand
 */
public class TransactionRepositoryMapImpl implements TransactionRepository {

    private Map<String, Transaction> transactions;

    public TransactionRepositoryMapImpl() {
        Path path = Paths.get("C:/Users/leand/Develop/Workspaces/Projects/Estudos/Cursos/JavaElite/Modulo_01/modulo1-projeto-zenon-bank/data/PS_20174392719_1491204439457_log.csv");
        TransactionIngestor ingestor = new TransactionIngestor(path);
        this.transactions = ingestor.getTransactionMap();
    }

    @Override
    public Optional<Transaction> findByOriginCustomerName(String name) {
        long startTime = System.nanoTime();
        Transaction transaction = this.transactions.get(name);
        long endTime = System.nanoTime();
        IO.println("Tempo gasto pela pesquisa no mapa: " + (endTime - startTime) + "ns");
        return Optional.ofNullable(transaction);
    }
    
}
