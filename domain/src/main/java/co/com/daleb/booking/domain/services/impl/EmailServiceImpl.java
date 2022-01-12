package co.com.daleb.booking.domain.services.impl;

import co.com.daleb.booking.domain.exceptions.BookingException;
import co.com.daleb.booking.domain.exceptions.InternalServerError;
import co.com.daleb.booking.domain.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public String sendEmail(String receiver, String subject, String templateCode, String currentName) throws BookingException {



        return null;
    }

    private void sendEmail(final String receiver, final String subject, final String template) throws BookingException {
        final MimeMessage email = javaMailSender.createMimeMessage();
        final MimeMessageHelper content;
        try {
            content = new MimeMessageHelper(email, true);
            content.setSubject(subject);
            content.setTo(receiver);
            content.setText(template, true);
        } catch (Exception exception) {
            throw new InternalServerError("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        javaMailSender.send(email);
    }
}
