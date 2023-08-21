CREATE TABLE Cliente (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  tipo_pessoa VARCHAR(20) NOT NULL,
  cpf_cnpj BIGINT NOT NULL,
  rg_ie BIGINT,
  data_cadastro DATE,
  ativo BOOLEAN
);

CREATE TABLE Telefone (
  id SERIAL PRIMARY KEY,
  numero VARCHAR(20) NOT NULL,
  cliente_id INT REFERENCES Cliente(id) ON DELETE CASCADE
);
