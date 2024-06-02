package com.example.clientes;

import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends CrudRepository<ClienteModel, Long> {
    public abstract ArrayList<ClienteModel> findByPreferencia(Integer preferencia);

}
