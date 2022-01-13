package co.com.daleb.booking.domain.services;

import co.com.daleb.booking.domain.jsons.PaymentConfirmRequestRest;
import co.com.daleb.booking.domain.jsons.PaymentIntentRequestRest;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface PaymentService {

    PaymentIntent paymentIntent(PaymentIntentRequestRest paymentIntentRequestRest) throws StripeException;

    PaymentIntent paymentConfirm(PaymentConfirmRequestRest paymentConfirmRequestRest) throws StripeException;

    PaymentIntent paymentCancel(String paymentId) throws StripeException;

}
