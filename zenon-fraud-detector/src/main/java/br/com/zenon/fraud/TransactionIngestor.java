package br.com.zenon.fraud;

import br.com.zenon.fraud.utils.CSVParser;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionIngestor {

    private static final Logger LOGGER = Logger.getLogger(TransactionIngestor.class.getName());
    private final Path path;
    private List<Transaction> transactions;

    public TransactionIngestor(Path path) {
        this.path = path;
    }

    private void readTransactions() {
        try {
            List<String> conteudo = Files.readAllLines(path);
            if (conteudo.size() > 100001) {
                transactions = CSVParser.contentParser(conteudo.subList(1, 100001));
            } else {
                transactions = CSVParser.contentParser(conteudo.subList(1, conteudo.size()));
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void processData() {
        if (Objects.isNull(this.transactions)) {
            this.readTransactions();
        }

        transactions.forEach(transaction -> {
            IO.println(transaction);
        });
    }

    public List<Transaction> getTransationsList() {
        if (Objects.isNull(this.transactions)) {
            this.readTransactions();
        }

        return this.transactions;
    }

    public Map<String, Transaction> getTransactionMap() {
        Map<String, Transaction> mapTransactions = new HashMap<>();
        try {
            List<String> conteudo = Files.readAllLines(path);
            mapTransactions = CSVParser.contentParserToMap(conteudo.subList(1, conteudo.size()));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return mapTransactions;
    }
}
