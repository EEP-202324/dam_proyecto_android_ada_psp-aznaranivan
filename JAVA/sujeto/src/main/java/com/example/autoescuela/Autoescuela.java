package com.example.autoescuela;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Autoescuela {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String logotipo;
    private int numeroCoches;

    public Autoescuela() {
    }

	public Autoescuela(Long id, String nombre, String logotipo, int numeroCoches) {
		this.id = id;
		this.nombre = nombre;
		this.logotipo = logotipo;
		this.numeroCoches = numeroCoches;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(String logotipo) {
		this.logotipo = logotipo;
	}

	public int getNumeroCoches() {
		return numeroCoches;
	}

	public void setNumeroCoches(int numeroCoches) {
		this.numeroCoches = numeroCoches;
	}


}