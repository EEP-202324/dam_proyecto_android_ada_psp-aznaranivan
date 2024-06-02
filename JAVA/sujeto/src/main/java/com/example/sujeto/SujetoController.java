package com.example.sujeto;

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
import com.example.sujeto.Sujeto;
import com.example.sujeto.SujetoRepository;

@RestController
@RequestMapping("/sujetos")
public class SujetoController {
	
	private final SujetoRepository sujetoRepository;

	private SujetoController(SujetoRepository sujetoRepository) {
		this.sujetoRepository = sujetoRepository;
	}

	@GetMapping
	private ResponseEntity<List<Sujeto>> findAll(Pageable pageable) {
		Page<Sujeto> page = sujetoRepository.findAll(PageRequest.of(pageable.getPageNumber(),
				pageable.getPageSize(), // 20
				pageable.getSortOr(Sort.by(Sort.Direction.ASC, "nombre"))));
		return ResponseEntity.ok(page.getContent());
	}

	@GetMapping("/{requestedId}")
	private ResponseEntity<Sujeto> findById(@PathVariable Long requestedId) {
		Optional<Sujeto> sujetoOptional = sujetoRepository.findById(requestedId);
		if (sujetoOptional.isPresent()) {
			return ResponseEntity.ok(sujetoOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	private ResponseEntity<Sujeto> createSujeto(@RequestBody Sujeto newSujetoRequested) {
		Sujeto savedSujeto = sujetoRepository.save(newSujetoRequested);
		return ResponseEntity.ok(savedSujeto);
	}

	@PutMapping("/{id}")
	private ResponseEntity<Void> updateSujeto(@PathVariable Long id, @RequestBody Sujeto updatedSujeto) {
		if (!sujetoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		updatedSujeto.setId(id);
		sujetoRepository.save(updatedSujeto);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<Void> deleteSujeto(@PathVariable Long id) {
		if (!sujetoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		sujetoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}