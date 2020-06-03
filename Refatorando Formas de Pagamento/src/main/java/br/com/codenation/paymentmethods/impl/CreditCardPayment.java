package br.com.codenation.paymentmethods.impl;

import br.com.codenation.paymentmethods.PaymentMethod;
import br.com.codenation.paymentmethods.PriceStrategy;

public class CreditCardPayment implements PriceStrategy {


    @Override
    public Double calculate(Double price) {
        return price * 0.98;
    }
}
