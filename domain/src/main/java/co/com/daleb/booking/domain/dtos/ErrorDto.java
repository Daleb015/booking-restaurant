package co.com.daleb.booking.domain.dtos;

import java.io.Serializable;

public class ErrorDto implements Serializable {

    private String name;

    private String value;

    public ErrorDto(String name, String value) {
        this.name = name;
        this.value = value;
    }

}
