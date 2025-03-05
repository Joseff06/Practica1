package co.edu.uniandes.dse.parcialprueba.repositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
    List<PacienteEntity> findByName(String name);
} 