package zab.romik.usermanagement.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import zab.romik.usermanagement.usermanagement.BusinessLogicException;
import zab.romik.usermanagement.usermanagement.MissingDataException;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<?> handleBusinessLogic(BusinessLogicException e) {
        return ResponseEntity.badRequest()
                .body(new ExceptionView(e.getMessage()));
    }

    @ExceptionHandler(MissingDataException.class)
    public ResponseEntity<?> handleMissingDataEx(MissingDataException e) {
        return ResponseEntity.notFound().build();
    }

    private static class ExceptionView {
        private final String message;

        ExceptionView(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
