package com.cadastroCliente.CadastrodeCliente.repository;

import com.cadastroCliente.CadastrodeCliente.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Integer> {
}
