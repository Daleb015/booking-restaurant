package co.com.daleb.booking.domain.exceptions;

import co.com.daleb.booking.domain.dtos.ErrorDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Data
@EqualsAndHashCode(callSuper = true)
public class InternalServerError extends BookingException {

    public InternalServerError(String code, String message) {
        super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public InternalServerError(String code, String message, ErrorDto data) {
        super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message, Arrays.asList(data));
    }

}
