package com.github.cazzruandev.springapirestbeginner.api.controller;

import com.github.cazzruandev.springapirestbeginner.api.representationmodel.input.ClienteInput;
import com.github.cazzruandev.springapirestbeginner.api.representationmodel.output.ClienteOutput;
import com.github.cazzruandev.springapirestbeginner.domain.model.Cliente;
import com.github.cazzruandev.springapirestbeginner.domain.service.ICadastroCliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ICadastroCliente cadastroClienteService;

    @GetMapping
    public List<ClienteOutput> findAllCliente(){
        List<Cliente> clientes = cadastroClienteService.findAllCliente();
        return toCollectionModel(clientes);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteOutput> findClienteById(@PathVariable Long clienteId){
        Cliente cliente = cadastroClienteService.findClienteById(clienteId);
        return new ResponseEntity<>(toModel(cliente), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> saveCliente(@RequestBody @Valid ClienteInput clienteInput){
        Cliente cliente = toEntity(clienteInput);
        cliente = cadastroClienteService.saveCliente(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long clienteId, @RequestBody @Valid ClienteInput clienteInput){
        Cliente cliente = toEntity(clienteInput);
        cliente = cadastroClienteService.updateCliente(clienteId, cliente);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long clienteId){
        cadastroClienteService.deleteClienteById(clienteId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

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
