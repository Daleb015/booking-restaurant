package co.com.daleb.booking.domain.jsons;

import lombok.Data;

@Data
public class PaymentConfirmRequestRest {

  private String paymentId;

  private String name;

  private String email;

  private String locator;
}
