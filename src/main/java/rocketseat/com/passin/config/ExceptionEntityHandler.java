package rocketseat.com.passin.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rocketseat.com.passin.domain.attendee.exceptions.AttendeeAlreadyExistException;
import rocketseat.com.passin.domain.attendee.exceptions.AttendeeNotFoundExceptions;
import rocketseat.com.passin.domain.checkin.exceptions.CheckedInAlreadyExistsException;
import rocketseat.com.passin.domain.event.exceptions.EventFullException;
import rocketseat.com.passin.domain.event.exceptions.EventNotFoundExceptions;
import rocketseat.com.passin.dto.general.ErroResponseDTO;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(EventNotFoundExceptions.class)
    public ResponseEntity handleEventNotFound(EventNotFoundExceptions exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(EventFullException.class)
    public ResponseEntity<ErroResponseDTO> handleEventFull(EventFullException exception){
        return ResponseEntity.badRequest().body(new ErroResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(AttendeeNotFoundExceptions.class)
    public ResponseEntity handleAttendeeNotFound(AttendeeNotFoundExceptions exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AttendeeAlreadyExistException.class)
    public ResponseEntity handleAttendeeAlreadyExists(AttendeeAlreadyExistException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(CheckedInAlreadyExistsException.class)
    public ResponseEntity handleCheckInAlreadyExists(CheckedInAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}
