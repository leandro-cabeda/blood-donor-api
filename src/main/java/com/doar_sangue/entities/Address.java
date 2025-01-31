package com.doar_sangue.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Embeddable
public class Address {

    @NotBlank(message = "O CEP é obrigatório.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 00000-000.")
    private  String cep;

    @NotBlank(message = "O endereço é obrigatório.")
    private  String endereco;

    @NotBlank(message = "O bairro é obrigatório.")
    private  String bairro;

    @NotBlank(message = "A cidade é obrigatória.")
    private  String cidade;

    @NotBlank(message = "O estado é obrigatório.")
    @Pattern(regexp = "^[A-Z]{2}$", message = "O estado deve conter exatamente 2 letras maiúsculas (ex: SP, RJ).")
    private  String estado;

    @NotNull(message = "O número é obrigatório.")
    private  Integer numero;

    public Address() {
    }

    public Address(String cep, String endereco, String bairro, String cidade, String estado, Integer numero) {
        this.cep = cep;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
    }
}
