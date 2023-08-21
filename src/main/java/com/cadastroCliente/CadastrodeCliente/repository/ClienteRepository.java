package com.cadastroCliente.CadastrodeCliente.repository;

import com.cadastroCliente.CadastrodeCliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsByCpfCnpj(Long cpfCnpj);
}
