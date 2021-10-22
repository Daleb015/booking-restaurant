package co.com.daleb.booking.domain.exceptions;

import co.com.daleb.booking.domain.dtos.ErrorDto;

import java.util.ArrayList;
import java.util.List;

public class BookingException extends Exception {

    private final String code;

    private final int responseCode;

    private final List<ErrorDto> errorList = new ArrayList<ErrorDto>();

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

    public String getCode() {
        return code;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public List<ErrorDto> getErrorList() {
        return errorList;
    }
}
