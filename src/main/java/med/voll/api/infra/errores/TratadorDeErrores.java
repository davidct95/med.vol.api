package med.voll.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
        //return ResponseEntity.badRequest().body(e.getFieldError());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity tratarError409(DataIntegrityViolationException e) {
        // Mensaje amigable para el cliente
        var mensaje = "Ya existe un registro con el mismo valor en un campo único.";

        // Puedes incluir detalles del error real si es necesario (opcional)
        var detalles = e.getMostSpecificCause().getMessage();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorDetalles(mensaje, detalles));

        //return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
    }

    private record DatosErrorValidacion(String campo, String error){
        public DatosErrorValidacion(FieldError e){
            this(e.getField(), e.getDefaultMessage());
        }
    }

    private record ErrorDetalles(String mensaje, String detalles) {}

}
