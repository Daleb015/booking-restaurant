package co.com.daleb.booking.domain.exceptions;

import co.com.daleb.booking.domain.dtos.ErrorDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BookingException extends Exception {

    private String code;

    private int responseCode;

    private final List<ErrorDto> errorList = new ArrayList<>();

    public BookingException(String code, int responseCode, String message){
        super(message);
        this.code=code;
        this.responseCode=responseCode;
    }

    public BookingException(String code, int responseCode, String message, List<ErrorDto> errorList) {
        super(message);
        this.code = code;
        this.responseCode = responseCode;
        this.errorList.addAll(errorList);
    }

}
