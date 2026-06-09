package br.com.zenon.fraud;

import br.com.zenon.fraud.enums.TransactionType;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FraudAnalyzer {

    private final List<Transaction> transactions;

    public FraudAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getListOfFrauds() {
        return this.transactions.stream().filter(t -> t.isFraud()).toList();
    }

    public void printFrauds() {
        List<Transaction> frauds = this.getListOfFrauds();
        IO.println("Total de fraudes: " + frauds.size());

        IO.println("Top 3 de fraudes de maior valor: ");
        frauds.stream()
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .limit(3)
                .forEach(f -> IO.println(f));
        List<String> suspects = frauds.stream()
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .distinct()
                .limit(5)
                .map(t -> {
                    return t.customerOrig().name();
                }).toList();

        IO.println("Clientes suspeitos: ");
        suspects.stream().forEach(s -> IO.println(s));

        BigDecimal total = frauds.stream()
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        IO.println("Prejuízo Total: " + total);

        IO.println("Fraudes por Tipo:");
        Map<TransactionType, Long> fraudsByGroup = frauds.stream()
                .collect(Collectors.groupingBy(Transaction::type,
                        Collectors.counting()));
        fraudsByGroup.forEach((TransactionType type, Long qtd) -> {
            IO.println(String.format("   - %-10s: %d", type, qtd));
        });
    }
}
