package com.example.autoescuela;



import java.net.URI;
import java.security.Principal;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.parameters.RequestBody;



@RestController
@CrossOrigin
@RequestMapping("/autoescuelas")
class AutoescuelaController {
	   private final AutoescuelaRepository autoescuelaRepository;

	   
	   public AutoescuelaController(AutoescuelaRepository autoescuelaRepository) {
	       this.autoescuelaRepository = autoescuelaRepository;
	   }

	   @GetMapping("/{requestedId}")
	    private ResponseEntity<Autoescuela> findById(@PathVariable Long requestedId, Principal principal) {
	        Autoescuela autoescuela = findAutoescuela(requestedId, principal);
	        if (autoescuela != null) {
	            return ResponseEntity.ok(autoescuela);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	   @PostMapping
	    private ResponseEntity<Void> createAutoescuela(@RequestBody Autoescuela newAutoescuelaRequest, 
	    		UriComponentsBuilder ucb, Principal principal) {
	        Autoescuela autoescuelaWithOwner = new Autoescuela(null, newAutoescuelaRequest.getNombre(), 
	        		newAutoescuelaRequest.getApellido(), newAutoescuelaRequest.getDNI(), principal.getName());
	        Autoescuela savedAutoescuela = autoescuelaRepository.save(autoescuelaWithOwner);
	        URI locationOfNewAutoescuela = ucb
	                .path("autoescuela/{id}")
	                .buildAndExpand(savedAutoescuela.getId())
	                .toUri();
	        return ResponseEntity.created(locationOfNewAutoescuela).build();
	    }
	   @GetMapping
	    private ResponseEntity<List<Autoescuela>> findAll(Pageable pageable, Principal principal) {
	        Page<Autoescuela> page = autoescuelaRepository.findByOwner(principal.getName(),
	                PageRequest.of(
	                        pageable.getPageNumber(),
	                        pageable.getPageSize(),
	                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "nombre"))
	                ));
	        return ResponseEntity.ok(page.getContent());
	    }
	   
	   @PutMapping("/{requestedId}")
	    private ResponseEntity<Void> putAutoescuela(@PathVariable Long requestedId, @RequestBody Autoescuela autoescuelaUpdate, Principal principal) {
	        Autoescuela autoescuela = findAutoescuela(requestedId, principal);
	        if (autoescuela != null) {
	            Autoescuela updatedAutoescuela = new Autoescuela(requestedId, autoescuelaUpdate.getNombre(), autoescuelaUpdate.getApellido(), autoescuelaUpdate.getDNI(), principal.getName());
	            autoescuelaRepository.save(updatedAutoescuela);
	            return ResponseEntity.noContent().build();
	        }
	        return ResponseEntity.notFound().build();
	    }

	    private Autoescuela findAutoescuela(Long requestedId, Principal principal) {
	        return autoescuelaRepository.findByIdAndOwner(requestedId, principal.getName());
	    }
}