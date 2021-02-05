package com.github.cazzruandev.springapirestbeginner.domain.service;

import com.github.cazzruandev.springapirestbeginner.domain.model.Cliente;

import java.util.List;

public interface ICadastroCliente {

    void verifyClienteExist(Long clienteId);
    Cliente findClienteById(Long clienteId);
    List<Cliente> findAllCliente();
    Cliente saveCliente(Cliente cliente);
    Cliente updateCliente(Long clienteId, Cliente cliente);
    void deleteClienteById(Long clienteId);

}
