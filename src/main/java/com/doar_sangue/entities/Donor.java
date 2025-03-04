package com.doar_sangue.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;

import java.time.LocalDate;


@Entity
@Table(name = "doadores")
@Getter
@Setter
public class Donor {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private  String nome;

    @Column(nullable = false, unique = true)
    private  String cpf;

    @Column(nullable = false)
    private  String rg;

    @Column(nullable = false)
    private  String sexo;

    @Column(columnDefinition = "TIMESTAMP", nullable = false, name = "data_nasc")
    private  LocalDate dataNasc;

    @Column(nullable = false)
    private  String mae;

    @Column(nullable = false)
    private  String pai;

    @Column(nullable = false, unique = true)
    private  String email;

    @Column(nullable = false, name = "telefone_fixo")
    private  String telefoneFixo;

    @Column(nullable = false)
    private  String celular;

    @Column(nullable = false)
    private  double peso;

    @Column(nullable = false)
    private  double altura;

    @Column(nullable = false, name = "tipo_sanguineo")
    private  String tipoSanguineo;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate createdAt;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate updatedAt;

    @Embedded
    private  Address endereco;

    public Donor() {}

    public Donor(Long id, String nome, String rg, String cpf, String sexo, LocalDate dataNasc, String mae, String pai, String email, String telefoneFixo, String celular, double peso, double altura, String tipoSanguineo, Address endereco) {
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
}

