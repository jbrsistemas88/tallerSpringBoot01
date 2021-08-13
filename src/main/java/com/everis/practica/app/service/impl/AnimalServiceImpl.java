package com.everis.practica.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.practica.app.domain.Animal;
import com.everis.practica.app.repository.IRepositoryAnimal;
import com.everis.practica.app.service.IAnimalService;

@Service
public class AnimalServiceImpl implements IAnimalService {

	@Autowired
	public IRepositoryAnimal repo;
	
	@Override
	public Animal guardarAnimal(Animal a) {
		// TODO Auto-generated method stub
		return repo.save(a);
	}

	@Override
	public List<Animal> listarAnimal() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Animal actualizarAnimal(Long id, Animal a) {
		// TODO Auto-generated method stub		
		return repo.save(a);
	}

	@Override
	public void eliminarAnimal(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	@Override
	public Animal buscarAnimal(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Override
	public Optional<Animal> buscarPorCodigo(Long Codigo) {
		// TODO Auto-generated method stub
		return repo.findByCodigo(Codigo);		
	}

}
