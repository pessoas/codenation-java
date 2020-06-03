package br.com.codenation;

import br.com.codenation.paymentmethods.PaymentMethod;

public class BillingProcessor {

    public Double calculate(Order order) {
        switch (order.getPaymentMethod()) {
            case CASH:
                return PaymentMethod.CASH.getPaymentStrategy().calculate(order.getPrice());
            case TRANSFER:
                return PaymentMethod.TRANSFER.getPaymentStrategy().calculate(order.getPrice());
            case CREDIT_CARD:
                return PaymentMethod.CREDIT_CARD.getPaymentStrategy().calculate(order.getPrice());
            case DEBIT_CARD:
                return PaymentMethod.DEBIT_CARD.getPaymentStrategy().calculate(order.getPrice());
        }
        throw new RuntimeException("Payment method not implemented");
    }
}