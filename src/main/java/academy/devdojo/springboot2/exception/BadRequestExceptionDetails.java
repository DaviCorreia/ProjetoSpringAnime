package academy.devdojo.springboot2.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BadRequestExceptionDetails {
    public String title;
    private int status;
    private String details;
    private String developerMessage;
    private LocalDateTime timestamp;

}
