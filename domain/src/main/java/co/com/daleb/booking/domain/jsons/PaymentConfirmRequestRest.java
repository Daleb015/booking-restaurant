package co.com.daleb.booking.domain.jsons;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class PaymentConfirmRequestRest {
    private String paymentId;
}
