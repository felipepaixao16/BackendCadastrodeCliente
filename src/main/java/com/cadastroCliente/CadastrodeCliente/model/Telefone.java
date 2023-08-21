package com.cadastroCliente.CadastrodeCliente.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(length = 11)
    private String numero;
}