import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.services.ConsultaMedicaService;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de lógica de ConsultaMedicaService
 */
@DataJpaTest
@Transactional
@Import(ConsultaMedicaService.class)
class ConsultaMedicaServiceTest {

    @Autowired
    private ConsultaMedicaService consultaMedicaService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<ConsultaMedicaEntity> consultaMedicaList = new ArrayList<>();
    private PacienteEntity pacienteEntity;

    /**
     * Configuración inicial de la prueba.
     */
    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from ConsultaMedicaEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from PacienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() {
        pacienteEntity = factory.manufacturePojo(PacienteEntity.class);
        entityManager.persist(pacienteEntity);

        for (int i = 0; i < 3; i++) {
            ConsultaMedicaEntity consultaMedicaEntity = factory.manufacturePojo(ConsultaMedicaEntity.class);
            consultaMedicaEntity.setPaciente(pacienteEntity);
            entityManager.persist(consultaMedicaEntity);
            consultaMedicaList.add(consultaMedicaEntity);
        }
    }

    /**
     * Prueba para crear una consulta médica correctamente.
     */
    @Test
    void testCreateConsulta() throws EntityNotFoundException, IllegalOperationException {
        ConsultaMedicaEntity newConsultaMedica = factory.manufacturePojo(ConsultaMedicaEntity.class);
        newConsultaMedica.setPaciente(pacienteEntity);

        // Establecer la fecha de la consulta a una fecha futura
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        newConsultaMedica.setFecha(calendar.getTime());

        ConsultaMedicaEntity result = consultaMedicaService.createConsulta(newConsultaMedica);
        assertNotNull(result);

        ConsultaMedicaEntity entity = entityManager.find(ConsultaMedicaEntity.class, result.getId());
        assertNotNull(entity);
        assertEquals(newConsultaMedica.getFecha(), entity.getFecha());
        assertEquals(newConsultaMedica.getPaciente(), entity.getPaciente());
    }
}