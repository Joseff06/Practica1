package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;

import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;

import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.ConsultaMedicaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsultaMedicaService {

	@Autowired
	ConsultaMedicaRepository consultaMedicaRepository;

	@Autowired
	PacienteRepository pacienteRepository;
	@Transactional

	public ConsultaMedicaEntity createConsulta(ConsultaMedicaEntity consultaMedica) throws EntityNotFoundException, IllegalOperationException {
	    log.info("Inicia proceso de creación de la consulta médica");
	    
	    if (consultaMedica.getPaciente() == null) {
	        throw new IllegalOperationException("La consulta no tiene un paciente asociado");
	    }
	    // no usé el metodo recomendado por el enunciado pero use este basandome en el que se usa en el ejemplo de AuthorService
	    Calendar calendar = Calendar.getInstance();
	    if (consultaMedica.getFecha().compareTo(calendar.getTime()) <= 0) {
	        throw new IllegalOperationException("La fecha de la consulta debe ser mayor a la fecha actual");
	    }

	    log.info("Termina proceso de creación de la consulta médica");
	    return consultaMedicaRepository.save(consultaMedica);
	}
}