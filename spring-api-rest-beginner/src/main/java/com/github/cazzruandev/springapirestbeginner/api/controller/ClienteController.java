package com.github.cazzruandev.springapirestbeginner.api.controller;

import com.github.cazzruandev.springapirestbeginner.api.representationmodel.input.ClienteInput;
import com.github.cazzruandev.springapirestbeginner.api.representationmodel.output.ClienteOutput;
import com.github.cazzruandev.springapirestbeginner.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    public ModelMapper modelMapper;

    private Cliente toEntity(ClienteInput clienteInput){
        return  modelMapper.map(clienteInput, Cliente.class);
    }

    private ClienteOutput toModel(Cliente cliente){
        return modelMapper.map(cliente, ClienteOutput.class);
    }

    private List<ClienteOutput> toCollectionModel(List<Cliente> clientes){
        return clientes.stream()
                .map(cliente -> toModel(cliente))
                .collect(Collectors.toList());
    }

}
