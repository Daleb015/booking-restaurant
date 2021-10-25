package co.com.daleb.booking.domain.exceptions;

import co.com.daleb.booking.domain.dtos.ErrorDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.Arrays;


@Data
@EqualsAndHashCode(callSuper = true)
public class NotFoundException extends BookingException {

    public NotFoundException(String code, String message) {
        super(code, HttpStatus.NOT_FOUND.value(), message);
    }

    public NotFoundException(String code, String message, ErrorDto data) {
        super(code, HttpStatus.NOT_FOUND.value(), message, Arrays.asList(data));
    }

}
