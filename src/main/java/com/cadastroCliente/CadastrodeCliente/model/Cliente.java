package com.cadastroCliente.CadastrodeCliente.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Cliente {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo_pessoa;

    @Column(length = 14, name = "cpf_cnpj", nullable = false)
    private Long cpfCnpj;

    @Column(length = 10)
    private Long rg_ie;

    @Column
    private LocalDate data_cadastro;

    @Column
    private Boolean ativo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cliente_id")
    private List<Telefone> telefones = new ArrayList<>();
}
