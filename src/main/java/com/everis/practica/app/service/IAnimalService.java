package com.everis.practica.app.service;

import java.util.List;
import java.util.Optional;

import com.everis.practica.app.domain.Animal;

public interface IAnimalService {

	public Animal guardarAnimal(Animal a);
	public List<Animal> listarAnimal();
	public Animal actualizarAnimal(Long id, Animal a);
	public void eliminarAnimal(Long id);
	public Animal buscarAnimal(Long id);
	
	public Optional<Animal> buscarPorCodigo(Long Codigo);
	
}
