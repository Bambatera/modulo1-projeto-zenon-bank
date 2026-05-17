package br.com.zenon.fraud;

import br.com.zenon.fraud.utils.CSVParser;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionIngestor {

    private static final Logger LOGGER = Logger.getLogger(TransactionIngestor.class.getName());
    private final Path path;

    public TransactionIngestor(Path path) {
        this.path = path;
    }

    public void processData() {
        try {
            List<Transaction> transactions;
            List<String> conteudo = Files.readAllLines(path);
            if (conteudo.size() > 1001) {
                transactions = CSVParser.contentParser(conteudo.subList(1, 1001));
            } else {
                transactions = CSVParser.contentParser(conteudo.subList(1, conteudo.size()));
            }

            transactions.forEach(transaction -> {
                IO.println(transaction);
            });
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

    }

}
