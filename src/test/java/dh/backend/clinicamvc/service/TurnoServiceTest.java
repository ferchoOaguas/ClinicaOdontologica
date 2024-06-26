package dh.backend.clinicamvc.service;


import dh.backend.clinicamvc.Dto.request.TurnoRequestDto;
import dh.backend.clinicamvc.Dto.response.OdontologoResponseDto;
import dh.backend.clinicamvc.Dto.response.PacienteResponseDto;
import dh.backend.clinicamvc.Dto.response.TurnoResponseDto;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.entity.Turno;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.service.impl.OdontologoService;
import dh.backend.clinicamvc.service.impl.PacienteService;
import dh.backend.clinicamvc.service.impl.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class TurnoServiceTest {


    @Autowired
    private TurnoService turnoService;


    @Autowired
    private PacienteService pacienteService;


    @Autowired
    private OdontologoService odontologoService;


    private PacienteResponseDto paciente;
    private OdontologoResponseDto odontologo;
    private TurnoRequestDto turno;
    /*Objeto de mapeo*/
    private ModelMapper modelMapper;


    @BeforeEach
    public void setUp() {
        // Registra un nuevo paciente
        Paciente nuevoPaciente = new Paciente();
        nuevoPaciente.setNombre("Roberto");
        nuevoPaciente.setApellido("Sanchéz");
        nuevoPaciente.setDni("16589698");
        nuevoPaciente.setFechaIngreso(LocalDate.of(2023, 4, 20));
        paciente = pacienteService.registrar(nuevoPaciente); // Asegúrate de que este método retorna un PacienteResponseDto


        // Registra un nuevo odontólogo
        Odontologo nuevoOdontologo = new Odontologo();
        nuevoOdontologo.setNombre("Carlos");
        nuevoOdontologo.setApellido("Vazquez");
        nuevoOdontologo.setNroMatricula("12563");
        odontologo = odontologoService.registrar(nuevoOdontologo); // Asegúrate de que este método retorna un OdontologoResponseDto


        // Crea el DTO del turno usando los IDs del paciente y odontólogo recién registrados
        turno = new TurnoRequestDto();
        turno.setFecha("2023-04-20"); // Asegúrate de que el formato de la fecha es correcto
        turno.setPaciente_id(paciente.getId());
        turno.setOdontologo_id(odontologo.getId());
    }


    @Test
    public void testCrearTurno() throws BadRequestException {
        turnoService.registrar(turno);
        assertNotNull(turno);
    }

    @Test
    @DisplayName("Testear busqueda turno por id")
    public void testBuscarTurno() throws BadRequestException{
        TurnoResponseDto crearTurno =  turnoService.registrar(turno);
        Integer id = crearTurno.getId();
        TurnoResponseDto turnoEncontrado = turnoService.buscarPorId(id);
        assertEquals(id, turnoEncontrado.getId());
    }

    @Test
    @DisplayName("Testear busqueda todos los turnos")
    void testBusquedaTodos() {
        List<TurnoResponseDto> turnos = turnoService.buscarTodos();
        assertFalse(turnos.isEmpty());
    }
}

