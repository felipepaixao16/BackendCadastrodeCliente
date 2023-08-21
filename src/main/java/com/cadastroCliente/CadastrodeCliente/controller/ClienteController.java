package com.cadastroCliente.CadastrodeCliente.controller;

import com.cadastroCliente.CadastrodeCliente.dto.ClienteRequestDto;
import com.cadastroCliente.CadastrodeCliente.model.Cliente;
import com.cadastroCliente.CadastrodeCliente.repository.ClienteRepository;
import com.cadastroCliente.CadastrodeCliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteService service;

    @GetMapping
    public List<Cliente> pegarTodos() {
        return repository.findAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Integer id) {
        var clienteOptional = repository.findById(id);
        if (clienteOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return clienteOptional.get();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid ClienteRequestDto clienteRequestDto) {
        service.salvar(clienteRequestDto);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public Cliente atualizarPorId(@PathVariable Integer id, @RequestBody Cliente cliente) {
        var clienteOptional = repository.findById(id);
        if (clienteOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        cliente.setId(id);
        return repository.save(cliente);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirPorId(@PathVariable Integer id) {
        var clienteOptional = repository.findById(id);
        repository.delete(clienteOptional.get());
    }

    // Métodos Telefone
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/{id}/adicionar-telefone")
    public void adicionarTelefone(@PathVariable Integer id, @RequestBody ClienteRequestDto.TelefoneDto telefoneDto) {
        service.adicionarTelefone(id, telefoneDto);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}/telefones/{telefoneId}")
    public void excluirTelefone(@PathVariable Integer id, @PathVariable Integer telefoneId) {
        service.excluirTelefone(id, telefoneId);
    }
}
