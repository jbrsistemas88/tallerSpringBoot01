package com.everis.practica.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.practica.app.domain.Animal;

@Repository
public interface IRepositoryAnimal extends JpaRepository<Animal, Long> {
	
	public Optional<Animal> findByCodigo(Long Codigo);
	
}
