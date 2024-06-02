package com.example.clientes;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    
    public ArrayList<ClienteModel> obtenerClientes(){
        return (ArrayList<ClienteModel>) clienteRepository.findAll();
    }

    public ClienteModel guardarCliente(ClienteModel cliente){
        return clienteRepository.save(cliente);
    }

    public Optional<ClienteModel> obtenerPorId(Long id){
        return clienteRepository.findById(id);
    }


    public ArrayList<ClienteModel>  obtenerPorPreferencia(Integer preferencia) {
        return clienteRepository.findByPreferencia(preferencia);
    }

    public boolean eliminarCliente(Long id) {
        try{
            clienteRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }


    
}