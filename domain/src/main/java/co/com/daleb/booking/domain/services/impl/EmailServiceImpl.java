package co.com.daleb.booking.domain.services.impl;

import co.com.daleb.booking.domain.dtos.EmailTemplateDTO;
import co.com.daleb.booking.domain.exceptions.BookingException;
import co.com.daleb.booking.domain.exceptions.InternalServerError;
import co.com.daleb.booking.domain.exceptions.NotFoundException;
import co.com.daleb.booking.domain.services.EmailService;
import co.com.daleb.booking.infraestructure.sql.entities.NotificationEntity;
import co.com.daleb.booking.infraestructure.sql.jpa.NotificationRepository;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender javaMailSender;

  private final NotificationRepository notificationRepository;

  @Override
  public String sendEmail(String receiver, String templateCode, String currentName) throws BookingException {
    final EmailTemplateDTO emailTemplateDTO = findTemplateAndReplace(templateCode, currentName);

    processEmail(receiver, emailTemplateDTO.getSubject(), emailTemplateDTO.getTemplate());

    return "EMAIL_SEND";
  }

  private void processEmail(final String receiver, final String subject, final String template)
    throws BookingException {
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

  private EmailTemplateDTO findTemplateAndReplace(final String templateCode, final String currentName)
    throws BookingException {
    NotificationEntity notificationEntity = notificationRepository
      .findByTemplateCode(templateCode)
      .orElseThrow(() -> new NotFoundException("TEMPLATE_NOT_FOUND", "TEMPLATE_NOT_FOUND"));

    return EmailTemplateDTO
      .builder()
      .templateCode(templateCode)
      .subject(templateCode)
      .template(notificationEntity.getTemplate().replaceAll("\\{name\\}", currentName))
      .build();
  }
}
