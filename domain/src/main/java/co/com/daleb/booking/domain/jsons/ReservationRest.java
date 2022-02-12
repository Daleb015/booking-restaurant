package co.com.daleb.booking.domain.jsons;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class ReservationRest {

  @JsonProperty("locator")
  private String locator;

  @JsonProperty("restaurantId")
  private String restaurantId;

  @JsonProperty("person")
  private Long person;

  @JsonProperty("turnId")
  private Long turnId;

  @JsonProperty("date")
  private LocalDate date;
}
