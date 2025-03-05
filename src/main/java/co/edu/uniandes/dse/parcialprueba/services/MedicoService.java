package co.edu.uniandes.dse.parcialprueba.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;

import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Author.
 *
 *
 */

@Slf4j
@Service
public class MedicoService {

	@Autowired
	MedicoRepository medicoRepository;
	
	/**
	 * Se encarga de crear un Author en la base de datos.
	 *
	 * @param medico Objeto de AuthorEntity con los datos nuevos
	 * @return Objeto de AuthorEntity con los datos nuevos y su ID.
	 * @throws IllegalOperationException 
	 */
	@Transactional
	public MedicoEntity createMedico(MedicoEntity medico) throws IllegalOperationException {
		log.info("Inicia proceso de creación del autor");
		if (!medico.getRegistroMedico().startsWith("RM")) {
			throw new IllegalOperationException("El registro medico no es valido");
	    }
        
		return medicoRepository.save(medico);
	}}
