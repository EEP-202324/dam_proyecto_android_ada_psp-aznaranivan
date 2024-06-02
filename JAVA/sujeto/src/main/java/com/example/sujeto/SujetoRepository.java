package com.example.sujeto;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.example.sujeto.Sujeto;

@Repository
public interface SujetoRepository extends CrudRepository<Sujeto, Long>, PagingAndSortingRepository<Sujeto, Long> {
	
}