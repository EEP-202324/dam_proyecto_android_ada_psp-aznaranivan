package com.example.autoescuela.model;
import org.springframework.data.annotation.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Table
public class Autoescuela {
    @Id
    private Long id;
    private String nombre;
    private String direccion;
    private String sucursal;

    public Autoescuela() {
    }

    public Autoescuela(Long id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
     
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public String toString() {
        return "Autoescuela{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", sucursal='" + sucursal + '\'' +
                '}';
    }
}
