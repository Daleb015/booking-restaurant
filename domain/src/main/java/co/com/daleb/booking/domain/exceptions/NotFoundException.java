package co.com.daleb.booking.domain.exceptions;

public class NotFoundException extends BookingException{
    public NotFoundException(String code, String message) {
        super(code, 10 , message);
    }
}
