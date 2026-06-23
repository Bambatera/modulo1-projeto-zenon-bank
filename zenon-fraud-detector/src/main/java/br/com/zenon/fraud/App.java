package br.com.zenon.fraud;

import java.util.Objects;
import java.util.Scanner;

class App {

    private TransactionRepositoryListImpl listRepo = new TransactionRepositoryListImpl();
    private TransactionRepositoryMapImpl mapRepo = new TransactionRepositoryMapImpl();

    void main() {
        Scanner scanner = new Scanner(System.in);
        IO.print("Informe o nome a ser pesquisado: ");
        String name = scanner.nextLine();
        
        try {
            Transaction transaction = this.listRepo.findByOriginCustomerName(name)
                    .orElseThrow(() -> new RuntimeException("Transação não encontrada para o cliente " + name + "!"));
            IO.println(transaction);
            IO.println("-------------------------------------------------------------");
        } catch (RuntimeException e) {
            IO.println(e.getMessage());
        }
        
        try {
            Transaction transaction = this.mapRepo.findByOriginCustomerName(name)
                    .orElseThrow(() -> new RuntimeException("Transação não encontrada para o cliente " + name + "!"));
            if (!Objects.isNull(transaction)) {
                IO.println(transaction);
            }
            IO.println("-------------------------------------------------------------");
        } catch (RuntimeException e) {
            IO.println(e.getMessage());
        }
    }

}
