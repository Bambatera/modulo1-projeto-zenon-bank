package br.com.zenon.fraud;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class TransactionRepositoryListImpl implements TransactionRepository {

    private List<Transaction> transactions;

    public TransactionRepositoryListImpl() {
        Path path = Paths.get("C:/Users/leand/Develop/Workspaces/Projects/Estudos/Cursos/JavaElite/Modulo_01/modulo1-projeto-zenon-bank/data/PS_20174392719_1491204439457_log.csv");
        TransactionIngestor ingestor = new TransactionIngestor(path);
        this.transactions = ingestor.getTransationsList();
    }

    @Override
    public Optional<Transaction> findByOriginCustomerName(String name) {
        long startTime = System.nanoTime();
        Optional<Transaction> optTransaction = this.transactions.stream()
                .filter(t -> t.customerOrig().name().equalsIgnoreCase(name))
                .findFirst();
        long endTime = System.nanoTime();
        IO.println("Tempo gasto pela pesquisa na lista: " + (endTime - startTime) + "ns");
        return optTransaction;
    }
}
