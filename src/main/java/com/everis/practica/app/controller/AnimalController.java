package com.everis.practica.app.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.practica.app.domain.Animal;
import com.everis.practica.app.repository.IRepositoryAnimal;
import com.everis.practica.app.service.IAnimalService;

@RestController
@RequestMapping("/animales")
public class AnimalController {
	
	@Autowired
	public IAnimalService animalservice;
	
	@Autowired
	public IRepositoryAnimal repo;
	
	@PostMapping("/guardar")
	public ResponseEntity<?> metodoGuardarAnimal(@RequestBody Animal a) {	
		
		if(a == null) {
			return new ResponseEntity<>(new String("No has ingresado datos"), HttpStatus.NO_CONTENT);
		}else {
			Optional<Animal> animal = animalservice.buscarPorCodigo(a.getCodigo());
			if(!animal.isPresent()) {
				repo.save(a);
				return new ResponseEntity<>(new String("El animal se creo correctamente"), HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>(new String("Ya existe un animal con ese codigo"), HttpStatus.NOT_ACCEPTABLE);
			}
			//return new ResponseEntity<>(animalCreado, HttpStatus.CREATED);
		}		
		
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> metodoListarAnimal(){
		List<Animal> animales = animalservice.listarAnimal();
		if(animales.isEmpty()) {
			return ResponseEntity.notFound().build();
			//return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(animales, HttpStatus.OK);
		}		
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<?> metodoActualizarAnimal(@RequestBody Animal a, @PathVariable Long codigo){
		Optional<Animal> buscaanimal = repo.findByCodigo(codigo);
		
		if(!buscaanimal.isPresent()) {
			return ResponseEntity.notFound().build();
			//return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {			
			Animal actualizadoAnimal = animalservice.buscarAnimal(buscaanimal.get().getIdAnimal());
			actualizadoAnimal.setNombre(a.getNombre());
			actualizadoAnimal.setEdad(a.getEdad());
			actualizadoAnimal.setEspecie(a.getEspecie());			
			repo.save(actualizadoAnimal);
			//return ResponseEntity.ok(actualizadoAnimal);
			return new ResponseEntity<>(new String("Se actualizo correctamente"), HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Object> metodoEliminarAlumno(@RequestBody Animal a, @PathVariable Long id) {
		Animal animal = animalservice.buscarAnimal(id);
		if(animal == null) {
			return ResponseEntity.notFound().build();
			//return new ResponseEntity<>(new String("No existe el animal"), HttpStatus.NO_CONTENT);
		}else {
			animalservice.eliminarAnimal(id);
			return ResponseEntity.noContent().build();
		}
		
	}
	
	@GetMapping("/buscar/{codigo}")
	public ResponseEntity<?> metodoBuscarAnimalPorCodigo(@PathVariable Long codigo) {
		Optional<Animal> animal = animalservice.buscarPorCodigo(codigo);
		if(!animal.isPresent()) {
			return new ResponseEntity<>(new String("No existe el animal"), HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(animal.get(), HttpStatus.OK);
		}
	}

}
