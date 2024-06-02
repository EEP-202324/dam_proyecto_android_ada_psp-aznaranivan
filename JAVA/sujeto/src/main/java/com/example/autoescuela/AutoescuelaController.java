package com.example.autoescuela;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.autoescuela.Autoescuela;
import com.example.sujeto.Sujeto;
import com.example.autoescuela.AutoescuelaRepository;
import com.example.sujeto.SujetoRepository;

@RestController
@RequestMapping("/autoescuelas")
public class AutoescuelaController {
	
	private final AutoescuelaRepository autoescuelaRepository;
	
	private AutoescuelaController(AutoescuelaRepository autoescuelaRepository) {
		this.autoescuelaRepository = autoescuelaRepository;
	}
	
	@GetMapping
	private ResponseEntity<List<Autoescuela>> findAll(Pageable pageable) {
		Page<Autoescuela> page = autoescuelaRepository.findAll(PageRequest.of(pageable.getPageNumber(), // 0
				pageable.getPageSize(), // 20
				pageable.getSortOr(Sort.by(Sort.Direction.ASC, "nombre"))));
		return ResponseEntity.ok(page.getContent());
	}

	@GetMapping("/{Id}")
	private ResponseEntity<Autoescuela> findById(@PathVariable Long requestedId) {
		Optional<Autoescuela> autoescuelaOptional = autoescuelaRepository.findById(requestedId);
		if (autoescuelaOptional.isPresent()) {
			return ResponseEntity.ok(autoescuelaOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	private ResponseEntity<Autoescuela> createAutoescuela(@RequestBody Autoescuela newAutoescuelaRequested) {
		Autoescuela savedAutoescuela = autoescuelaRepository.save(newAutoescuelaRequested);
		return ResponseEntity.ok(savedAutoescuela);
	}

	@PutMapping("/{id}")
	private ResponseEntity<Void> updateAutoescuela(@PathVariable Long id, @RequestBody Autoescuela updatedAutoescuela) {
		if (!autoescuelaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		updatedAutoescuela.setId(id);
		autoescuelaRepository.save(updatedAutoescuela);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<Void> deleteAutoescuela(@PathVariable Long id) {
		if (!autoescuelaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		autoescuelaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
