package co.vison.demo.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
public class ErrorBody
{
    public ErrorBody(String message, List<String> details) {
        this.message = message;
        this.details = details;
    }

    private String message;

    private List<String> details;

}
