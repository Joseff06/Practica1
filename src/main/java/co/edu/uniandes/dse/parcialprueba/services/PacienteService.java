package co.edu.uniandes.dse.parcialprueba.services;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class PacienteService {

	@Autowired
	PacienteRepository pacienteRepository;

	@Transactional
	public PacienteEntity createPaciente(PacienteEntity pacienteEntity) throws IllegalOperationException {
		log.info("Inicia proceso de creación del paciente");
       
        log.info("Termina proceso de creación de la editorial");
        return pacienteRepository.save(pacienteEntity);
		
    }
	}