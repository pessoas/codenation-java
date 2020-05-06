package br.com.codenation.desafio.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador {

    private Long id;
    private Long idTime;
    private String nome;
    private LocalDate dataNascimento;
    private Integer nivelHabilidade;
    private BigDecimal salario;

    public Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {

        if(validateJogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario)){
            this.id = id;
            this.idTime = idTime;
            this.nome = nome;
            this.dataNascimento = dataNascimento;
            this.nivelHabilidade = nivelHabilidade;
            this.salario = salario;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTime() {
        return idTime;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getNivelHabilidade() {
        return nivelHabilidade;
    }

    public void setNivelHabilidade(Integer nivelHabilidade) {
        this.nivelHabilidade = nivelHabilidade;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public boolean validateJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        if(id == null ||
                idTime == null ||
                nome == null ||
                dataNascimento == null ||
                nivelHabilidade == null ||
                salario == null) {
            throw new NullPointerException("Argumentos invalidos");
        } else if (nivelHabilidade < 0 || nivelHabilidade > 100){
            throw new IllegalArgumentException("Nivel de habilidade incorreto");
        } else if (id < 0 || nome.trim().isEmpty() || salario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Id do jogador não pode ser negativo");
        }
        return true;
    }
}