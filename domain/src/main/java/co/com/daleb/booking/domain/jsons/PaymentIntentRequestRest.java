package co.com.daleb.booking.domain.jsons;

import lombok.Data;

@Data
public class PaymentIntentRequestRest {

  private String description;

  private Integer price;
}
