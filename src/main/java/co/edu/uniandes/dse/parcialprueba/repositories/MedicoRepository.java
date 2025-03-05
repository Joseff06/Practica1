package co.edu.uniandes.dse.parcialprueba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;

/**
 * Interface that persists an author
 *
 * @author ISIS2603
 *
 */
@Repository
public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {

}

