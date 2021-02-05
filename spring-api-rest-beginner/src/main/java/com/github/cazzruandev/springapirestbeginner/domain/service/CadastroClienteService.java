package com.github.cazzruandev.springapirestbeginner.domain.service;

import com.github.cazzruandev.springapirestbeginner.domain.exception.DuplicateException;
import com.github.cazzruandev.springapirestbeginner.domain.exception.EntityNotFoundException;
import com.github.cazzruandev.springapirestbeginner.domain.model.Cliente;
import com.github.cazzruandev.springapirestbeginner.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroClienteService implements ICadastroCliente{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void verifyClienteExist(Long clienteId) {
        clienteRepository.findById(clienteId).orElseThrow(
                () -> new EntityNotFoundException("Cliente ID: " + clienteId + " not found!"));
    }

    @Override
    public Cliente findClienteById(Long clienteId) {
        verifyClienteExist(clienteId);
        return clienteRepository.findById(clienteId).get();
    }

    @Override
    public List<Cliente> findAllCliente() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());

        if(clienteExistente != null && !clienteExistente.getId().equals(cliente.getId())){
            throw new DuplicateException("There is a customer with this e-mail registered!");
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Long clienteId, Cliente cliente) {
        verifyClienteExist(clienteId);
        cliente.setId(clienteId);
        return saveCliente(cliente);
    }

    @Override
    public void deleteClienteById(Long clienteId) {
        verifyClienteExist(clienteId);
        clienteRepository.deleteById(clienteId);
    }

}
