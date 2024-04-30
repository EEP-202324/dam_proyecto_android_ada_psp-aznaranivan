package com.example.autoescuela.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.autoescuela.model.Autoescuela;

@Repository
public interface AutoescuelaRepository extends CrudRepository<Autoescuela, Long>
	 {
	


}
