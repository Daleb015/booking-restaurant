package co.com.daleb.booking.domain.jsons;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateReservationRest {

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("person")
    private Long person;

    @JsonProperty("turnId")
    private Long turnId;

    @JsonProperty("restaurantId")
    private Long restaurantId;

}
