package com.cadastroCliente.CadastrodeCliente.service;

import com.cadastroCliente.CadastrodeCliente.dto.ClienteRequestDto;
import com.cadastroCliente.CadastrodeCliente.model.Cliente;
import com.cadastroCliente.CadastrodeCliente.model.Telefone;
import com.cadastroCliente.CadastrodeCliente.repository.ClienteRepository;
import com.cadastroCliente.CadastrodeCliente.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    public void salvar(ClienteRequestDto clienteRequestDto) {
        if (ClienteCadastrado(clienteRequestDto.getCpf_cnpj())) {
            throw new IllegalArgumentException("CPF/CNPJ já cadastrado.");
        } else {
            Cliente cliente = new Cliente();
            cliente.setNome(clienteRequestDto.getNome());
            cliente.setTipo_pessoa(clienteRequestDto.getTipo_pessoa());
            cliente.setCpfCnpj(clienteRequestDto.getCpf_cnpj());
            cliente.setRg_ie(clienteRequestDto.getRg_ie());
            cliente.setData_cadastro(clienteRequestDto.getData_cadastro());
            cliente.setAtivo(clienteRequestDto.getAtivo());
            List<Telefone> telefones = new ArrayList<>();
            for (ClienteRequestDto.TelefoneDto telefoneDto : clienteRequestDto.getTelefones()) {
                Telefone telefone = new Telefone();
                telefone.setNumero(telefoneDto.getNumero());
                telefones.add(telefone);
            }
            cliente.setTelefones(telefones);
            clienteRepository.save(cliente);
        }
    }
    private boolean ClienteCadastrado(Long cpfCnpj) {
        return clienteRepository.existsByCpfCnpj(cpfCnpj);
    }

    public void adicionarTelefone(Integer clienteId, ClienteRequestDto.TelefoneDto telefoneDto) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        Telefone telefone = new Telefone();
        telefone.setNumero(telefoneDto.getNumero());
        cliente.getTelefones().add(telefone);
        clienteRepository.save(cliente);
    }

    public void excluirTelefone(Integer clienteId, Integer telefoneId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        Telefone telefoneParaExcluir = null;
        for (Telefone telefone : cliente.getTelefones()) {
            if (telefoneId.equals(telefone.getId())) {
                telefoneParaExcluir = telefone;
                break;
            }
        }
        if (telefoneParaExcluir == null) {
            throw new IllegalArgumentException("Telefone não encontrado");
        }
        cliente.getTelefones().remove(telefoneParaExcluir);
        telefoneRepository.delete(telefoneParaExcluir);
        clienteRepository.save(cliente);
    }
}
