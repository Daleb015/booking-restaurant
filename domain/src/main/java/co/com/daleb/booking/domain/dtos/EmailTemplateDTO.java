package co.com.daleb.booking.domain.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class EmailTemplateDTO {

  private Long id;

  private String template;

  private String subject;

  private String templateCode;
}
