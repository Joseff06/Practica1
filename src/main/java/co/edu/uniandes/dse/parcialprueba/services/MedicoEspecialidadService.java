package co.edu.uniandes.dse.parcialprueba.services;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
	
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service        

public class MedicoEspecialidadService {
@Autowired
	private EspecialidadRepository especialidadRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	/**
	 * Asocia un Book existente a un Author
	 *
	 * @param medicoId Identificador de la instancia de Author
	 * @param especialidadId   Identificador de la instancia de Book
	 * @return Instancia de BookEntity que fue asociada a Author
	 */

	@Transactional
	public EspecialidadEntity addEspecialidad(Long medicoId, Long especialidadId) throws EntityNotFoundException {
		log.info("Inicia el proceso de agregar una especialidad a un medico con id = {}", medicoId);
		
		Optional<MedicoEntity> medicoEntity = medicoRepository.findById(medicoId);
		Optional<EspecialidadEntity> especialidadEntity = especialidadRepository.findById(especialidadId);

		if (medicoEntity.isEmpty()) {
			throw new EntityNotFoundException("El médico con el id " + medicoId + " no existe");
		}

		if (especialidadEntity.isEmpty()) {
			throw new EntityNotFoundException("La especialidad con el id " + especialidadId + " no existe");
		}

		medicoEntity.get().getEspecialidades().add(especialidadEntity.get());
		especialidadEntity.get().getMedicos().add(medicoEntity.get());

		log.info("Termina proceso de asociarle una especialidad al medico con id = {}", medicoId);
		return especialidadEntity.get();
	}
}
