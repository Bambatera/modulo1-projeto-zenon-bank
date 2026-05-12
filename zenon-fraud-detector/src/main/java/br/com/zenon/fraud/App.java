/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package br.com.zenon.fraud;

import br.com.zenon.fraud.enums.TransactionType;

/**
 *
 * @author leand
 */
class App {

    void main() {
        Transaction transaction1 = new Transaction(
                1,
                TransactionType.PAYMENT,
                9839.64,
                new Customer("C1231006815", 170136.0, 160296.36),
                new Customer("M1979787155", 0.0, 0.0),
                false,
                false);
        Transaction transaction2 = new Transaction(
                743,
                TransactionType.CASH_OUT,
                850002.52,
                new Customer("C1280323807", 850002.52, 0.0),
                new Customer("C873221189", 6510099.11, 7360101.63),
                true,
                false);
        
        IO.println("First Transaction:" + transaction1);
        IO.println("Seconde Transaction:" + transaction2);
    }
}
