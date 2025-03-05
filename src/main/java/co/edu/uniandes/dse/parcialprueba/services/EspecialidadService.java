package co.edu.uniandes.dse.parcialprueba.services;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;


import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class EspecialidadService {

	@Autowired
	EspecialidadRepository especialidadRepository;
	
	/**
	 * Se encarga de crear un Author en la base de datos.
	 *
	 * @param especialidad Objeto de AuthorEntity con los datos nuevos
	 * @return Objeto de AuthorEntity con los datos nuevos y su ID.
	 * @throws IllegalOperationException 
	 */
	@Transactional
	public EspecialidadEntity createEspecialidad(EspecialidadEntity especialidad) throws IllegalOperationException {
		log.info("Inicia proceso de creación del autor");
		if (especialidad.getDescripcion().length()<10) {
            throw new IllegalOperationException("La descripcion no es valida");
        }
		
        
		return especialidadRepository.save(especialidad);
	}}


