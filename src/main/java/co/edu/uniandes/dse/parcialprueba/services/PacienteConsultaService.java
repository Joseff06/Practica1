package co.edu.uniandes.dse.parcialprueba.services;


import java.util.List;
import java.util.Optional;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.ArrayList;

import jakarta.transaction.Transactional;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;

import co.edu.uniandes.dse.parcialprueba.repositories.ConsultaMedicaRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class PacienteConsultaService {

	@Autowired
	private ConsultaMedicaRepository consultaMedicaRepository;

	@Autowired
	private PacienteRepository pacienteRepository;
	
	
	@Transactional
	public ConsultaMedicaEntity addConsulta(Long consultaId, Long pacienteId) throws IllegalOperationException {
	    log.info("Inicia proceso de agregarle una consulta al paciente con id = {}", pacienteId);
	    
	    Optional<ConsultaMedicaEntity> consulta = consultaMedicaRepository.findById(consultaId);
	    if (consulta.isEmpty()) {
	        throw new IllegalOperationException("La consulta está vacía");
	    }
	    
	    Optional<PacienteEntity> paciente = pacienteRepository.findById(pacienteId);
	    if (paciente.isEmpty()) {
	        throw new IllegalOperationException("El paciente está vacío");
	    }
	    
	    // Verificar si el paciente ya tiene una consulta con la misma fecha recorriendo todas las consulta del paciente
	    for (ConsultaMedicaEntity existenteConsulta : paciente.get().getConsulta()) {
	        if (existenteConsulta.getFecha().equals(consulta.get().getFecha())) {
	            throw new IllegalOperationException("El paciente ya tiene una consulta asignada en la misma fecha");
	        }
	    }
	    
	    consulta.get().setPaciente(paciente.get());
	    log.info("Termina proceso de agregarle una consulta al paciente con id = {}", pacienteId);
	    return consulta.get();
	}

	@Transactional
	public List<ConsultaMedicaEntity> getConsultasProgramadas(Long pacienteId) throws IllegalOperationException {
	    log.info("Inicia proceso de buscar consultas vigentes asociadas al paciente con id = {}", pacienteId);
	    
	    Optional<PacienteEntity> paciente = pacienteRepository.findById(pacienteId);
	    if (paciente.isEmpty()) {
	        throw new IllegalOperationException("El paciente no existe");
	    }
	    
	    Calendar calendar = Calendar.getInstance();
	    Date currentDate = calendar.getTime();

	    List<ConsultaMedicaEntity> consultasProgramadas = new ArrayList<>(); // Inicializar la lista
	    
	    for (ConsultaMedicaEntity consultadate : paciente.get().getConsulta()) {
	        if (consultadate.getFecha().compareTo(currentDate) > 0) {
	            consultasProgramadas.add(consultadate);
	        }
	    }
	    
	    log.info("Termina proceso de buscar consultas vigentes asociadas al paciente con id = {}", pacienteId);
	    return consultasProgramadas;
	}
}
