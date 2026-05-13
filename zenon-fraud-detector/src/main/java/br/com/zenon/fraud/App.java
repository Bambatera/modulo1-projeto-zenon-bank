package br.com.zenon.fraud;

import java.nio.file.Path;
import java.nio.file.Paths;

class App {

    void main() {
        Path path = Paths.get("C:/Users/leand/Develop/Workspaces/Projects/Estudos/Cursos/JavaElite/Modulo_01/modulo1-projeto-zenon-bank/data/PS_20174392719_1491204439457_log.csv");
        TransactionIngestor ingestor = new TransactionIngestor(path);
        ingestor.processData();
    }

}
