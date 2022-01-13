package co.com.daleb.booking.domain.services.impl;

import co.com.daleb.booking.domain.jsons.PaymentConfirmRequestRest;
import co.com.daleb.booking.domain.jsons.PaymentIntentRequestRest;
import co.com.daleb.booking.domain.services.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.key.private}")
    private String stripePrivateKey;

    @Value("${stripe.key.public}")
    private String stripePublicKey;

    public enum Currency{
        USD,EUR;
    }

    @Override
    public PaymentIntent paymentIntent(PaymentIntentRequestRest paymentIntentRequestRest) throws StripeException {

        List<Object> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Stripe.apiKey = stripePrivateKey;

        Map<String,Object> params = new HashMap<>();
        params.put("currency",Currency.EUR);
        params.put("amount",paymentIntentRequestRest.getPrice());
        params.put("description",paymentIntentRequestRest.getDescription());
        params.put("payment_method_types",paymentMethodTypes);

        return PaymentIntent.create(params);
    }

    @Override
    public PaymentIntent paymentConfirm(PaymentConfirmRequestRest paymentConfirmRequestRest) throws StripeException {

        Stripe.apiKey = stripePrivateKey;

        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentConfirmRequestRest.getPaymentId());

        Map<String,Object> params = new HashMap<>();
        params.put("payment_method","pm_card_visa");
        paymentIntent.confirm(params);

        return paymentIntent;
    }

    @Override
    public PaymentIntent paymentCancel(String paymentId) throws StripeException {

        Stripe.apiKey = stripePrivateKey;

        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentId);

        paymentIntent.cancel();

        return paymentIntent;
    }
}
