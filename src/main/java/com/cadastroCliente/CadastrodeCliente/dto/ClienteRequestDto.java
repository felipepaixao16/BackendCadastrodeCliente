package com.cadastroCliente.CadastrodeCliente.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ClienteRequestDto {

        private String nome;

        private String tipo_pessoa;

        private Long cpf_cnpj;

        private Long rg_ie;

        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDate data_cadastro;

        private Boolean ativo;

        private List<TelefoneDto> telefones;

        @Getter
        @Setter
        public static class TelefoneDto {
                private String numero;
        }
}
