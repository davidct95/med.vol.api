package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String documentoIdentidad;
    private String telefono;
    @Embedded
    private Direccion direccion;
    private Boolean activo;


    public Paciente(@Valid DatosRegistroPaciente datos) {

        this.nombre = datos.nombre();
        this.email = datos.email();
        this.documentoIdentidad = datos.documentIdentidad();
        this.telefono = datos.telefono();
        this.direccion = new Direccion(datos.direccion());

    }

    public void actualizarInformacion(@Valid DatosActualizacionPaciente datos) {
        if(datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
        if(datos.telefono() != null) {
            this.telefono = datos.telefono();
        }
        if(datos.direccion() != null) {
            direccion.actualizarDatos(datos.direccion());
        }
    }

    public void inactivar() {
        this.activo = false;
    }
}
