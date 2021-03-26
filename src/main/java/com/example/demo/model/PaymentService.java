package com.example.demo.model;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@Service
public class PaymentService {
    private String secretKey = "sk_test_51IJ8ZKCwhlWVAVuWCPXNlbJhOqfuWDb0gY713dlN6YpRtzDpnL4oJywgaowUNBwp92mFWgXS4ApoavNzMtQn51Oy00L44gLaXw";

    public PaymentIntent paymentIntent(PaymentIntentDTO paymentIntentDTO) throws StripeException {
        Stripe.apiKey = secretKey;
        Map<String,Object> params = new HashMap<>();
        params.put("amount",paymentIntentDTO.getAmount());
        params.put("currency",paymentIntentDTO.getCurrency());
        params.put("description",paymentIntentDTO.getDescription());
        ArrayList payment_method_types = new ArrayList();
        payment_method_types.add("card");
        params.put("payment_method_types",payment_method_types);
        return PaymentIntent.create(params);
    }
    public PaymentIntent confirm(String id) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
        Map<String,Object> params = new HashMap<>();
        params.put("payment_method","pm_card_visa");
        paymentIntent.confirm();
        return paymentIntent;
    }

    public PaymentIntent cancel(String id) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
        paymentIntent.cancel();
        return paymentIntent;
    }

}