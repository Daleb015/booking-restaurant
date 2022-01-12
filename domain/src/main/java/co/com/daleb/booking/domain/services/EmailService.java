package co.com.daleb.booking.domain.services;

import co.com.daleb.booking.domain.exceptions.BookingException;

public interface EmailService {

    String sendEmail(final String receiver, final String subject, String templateCode, String currentName) throws BookingException;

}
