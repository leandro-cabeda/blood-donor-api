package com.doar_sangue.controllers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Representação de um endereço completo")
public class AddressDTO {

    @JsonProperty("cep")
    @NotEmpty
    @Schema(description = "CEP do endereço", example = "12345-678", required = true)
    private final String cep;

    @JsonProperty("endereco")
    @NotEmpty
    @Schema(description = "Endereço da residência ou estabelecimento", example = "Rua das Flores", required = true)
    private final String endereco;

    @JsonProperty("bairro")
    @NotEmpty
    @Schema(description = "Bairro onde o endereço está localizado", example = "Centro", required = true)
    private final String bairro;

    @JsonProperty("estado")
    @NotEmpty
    @Schema(description = "Estado do endereço", example = "São Paulo", required = true)
    private final String estado;

    @JsonProperty("cidade")
    @NotEmpty
    @Schema(description = "Cidade do endereço", example = "São Paulo", required = true)
    private final String cidade;

    @JsonProperty("numero")
    @NotNull
    @Schema(description = "Número do imóvel no endereço", example = "123", required = true)
    private final double numero;

    public AddressDTO(String cep, String estado, String bairro, String cidade, String endereco, double numero) {
        this.cep = cep;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
    }
}