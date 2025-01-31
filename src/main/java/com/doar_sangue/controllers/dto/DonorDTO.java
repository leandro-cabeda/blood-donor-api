package com.doar_sangue.controllers.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Representação de um doador")
public class DonorDTO {

    @JsonProperty("id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "Identificador único do doador", example = "1", required = false)
    private final Long id;

    @JsonProperty("nome")
    @NotEmpty
    @Schema(description = "Nome completo do doador", example = "João Silva", required = true)
    private final String nome;

    @JsonProperty("cpf")
    @NotEmpty
    @Schema(description = "CPF do doador", example = "123.456.789-00", required = true)
    private final String cpf;

    @JsonProperty("rg")
    @NotEmpty
    @Schema(description = "RG do doador", example = "12.345.678-9", required = true)
    private final String rg;

    @JsonProperty("sexo")
    @NotEmpty
    @Schema(description = "Sexo do doador", example = "Masculino", required = true)
    private final String sexo;

    @JsonProperty("data_nasc")
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Schema(description = "Data de nascimento do doador", example = "15/06/1985", required = true)
    private final LocalDate dataNasc;

    @JsonProperty("mae")
    @NotEmpty
    @Schema(description = "Nome da mãe do doador", example = "Maria Silva", required = true)
    private final String mae;

    @JsonProperty("pai")
    @NotEmpty
    @Schema(description = "Nome do pai do doador", example = "José Silva", required = true)
    private final String pai;

    @JsonProperty("email")
    @NotEmpty
    @Schema(description = "Email do doador", example = "joao.silva@example.com", required = true)
    private final String email;

    @JsonProperty("telefone_fixo")
    @NotEmpty
    @Schema(description = "Telefone fixo do doador", example = "(11) 1234-5678", required = true)
    private final String telefoneFixo;

    @JsonProperty("celular")
    @NotEmpty
    @Schema(description = "Celular do doador", example = "(11) 98765-4321", required = true)
    private final String celular;

    @JsonProperty("peso")
    @NotNull
    @Min(0)
    @Schema(description = "Peso do doador em kg", example = "75.5", required = true)
    private final double peso;

    @JsonProperty("altura")
    @NotNull
    @Min(0)
    @Schema(description = "Altura do doador em metros", example = "1.75", required = true)
    private final double altura;

    @JsonProperty("tipo_sanguineo")
    @NotEmpty
    @Schema(description = "Tipo sanguíneo do doador", example = "O+", required = true)
    private final String tipoSanguineo;

    @JsonProperty("created_at")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Schema(description = "Data de criação do doador", example = "01/01/2021", required = false)
    private LocalDate createdAt;

    @JsonProperty("updated_at")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Schema(description = "Data da última atualização do doador", example = "15/01/2021", required = false)
    private LocalDate updatedAt;

    @JsonProperty("endereco")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "Endereço do doador", required = false)
    private final AddressDTO endereco;

    public DonorDTO(Long id, String nome, String rg, String cpf, String sexo, LocalDate dataNasc, String mae, String pai, String email, String telefoneFixo, String celular, double peso, double altura, String tipoSanguineo, AddressDTO endereco) {
        this.id = id;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
        this.mae = mae;
        this.pai = pai;
        this.email = email;
        this.telefoneFixo = telefoneFixo;
        this.celular = celular;
        this.peso = peso;
        this.altura = altura;
        this.tipoSanguineo = tipoSanguineo;
        this.endereco = endereco;
    }

    public DonorDTO(DonorDTO dto) {
        this.id = dto.getId();
        this.endereco = dto.getEndereco();
        this.tipoSanguineo = dto.getTipoSanguineo();
        this.altura = dto.getAltura();
        this.peso = dto.getPeso();
        this.celular = dto.getCelular();
        this.telefoneFixo = dto.getTelefoneFixo();
        this.email = dto.getEmail();
        this.pai = dto.getPai();
        this.mae = dto.getMae();
        this.dataNasc = dto.getDataNasc();
        this.sexo = dto.getSexo();
        this.rg = dto.getRg();
        this.cpf = dto.getCpf();
        this.nome = dto.getNome();
    }
}
