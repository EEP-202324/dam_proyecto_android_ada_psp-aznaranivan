package com.example.autoescuela.controller;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.autoescuela.model.Autoescuela;
import com.example.autoescuela.repository.AutoescuelaRepository;

@RestController
@CrossOrigin
@RequestMapping("/Autoescuela")
public class AutoescuelaController {
	
	private final AutoescuelaRepository AutoescuelaRepository;
	
	private AutoescuelaController(AutoescuelaRepository AutoescuelaRepository) {
		this.AutoescuelaRepository = AutoescuelaRepository;
	}
	
//	// Apaño temporal para poder llamar desde Android, ya lo haremos bien
//	@GetMapping
//	private ResponseEntity<String> findAll() {
//		Iterable<CashCard> cashCardIterable = cashCardRepository.findAll();
//			return ResponseEntity.ok(cashCardIterable.toString());
//	}
	
//	@GetMapping()
//	private ResponseEntity<Iterable<CashCard>> findAll() {
//	   return ResponseEntity.ok(cashCardRepository.findAll());
//	}
	
	
	// violeta, anton, maurín, lucía, garcía, ema
	@PostMapping
    private ResponseEntity<Autoescuela> createAutoescuela(@RequestBody Autoescuela newAutoescuelaRequested) {
		Autoescuela savedEscuela = AutoescuelaRepository.save(newAutoescuelaRequested);
        return ResponseEntity.ok(savedEscuela);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Void> updateAutoescuela(@PathVariable Long id, @RequestBody Autoescuela updatedAutoescuela) {
        if (!AutoescuelaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedAutoescuela.setId(id);
        AutoescuelaRepository.save(updatedAutoescuela);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteAutoescuela(@PathVariable Long id) {
        if (!AutoescuelaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        AutoescuelaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
