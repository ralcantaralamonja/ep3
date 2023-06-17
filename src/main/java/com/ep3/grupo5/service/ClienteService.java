package com.ep3.grupo5.service;

import com.ep3.grupo5.entity.Cliente;
import com.ep3.grupo5.exception.ClienteNotFoundException;
import com.ep3.grupo5.repository.ClienteRepository;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> obtenerTodos(){
        return clienteRepository.findAll();
    }

    public Cliente obtenerporId(Long id){
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()){
            return optionalCliente.get();
        }else{
            throw new ClienteNotFoundException(id);
        }
    }

    public Cliente registrar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void eliminar(Long id){

        Optional<Cliente> optionalCliente= clienteRepository.findById(id);

        if (optionalCliente.isPresent()){
            clienteRepository.delete(optionalCliente.get());
        }else{
            throw new ClienteNotFoundException(id);
        }

    }
    public class ModificationDateListener {

        @PreUpdate
        public void preUpdate(Object entity) {
            if (entity instanceof Cliente) {
                ((Cliente) entity).preUpdate();
            }
        }
    }

}
