package co.com.daleb.booking.domain.jsons;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder(toBuilder = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingResponseRest<T> {

    private String status;
    private String code;
    private String message;
    private T data;

}
