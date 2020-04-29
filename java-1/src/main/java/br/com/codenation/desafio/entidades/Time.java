package br.com.codenation.desafio.entidades;

import java.time.LocalDate;

public class Time{
    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String getCorUniformeSecundario;

    private Long idCapitao;


    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String getCorUniformeSecundario) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.getCorUniformeSecundario = getCorUniformeSecundario;
    }

    public Long getId() {
        return id;
    }

    public Long getIdCapitao() {
        return idCapitao;
    }

    public void setIdCapitao(Long idCapitao) {
        this.idCapitao = idCapitao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCorUniformePrincipal() {
        return corUniformePrincipal;
    }

    public void setCorUniformePrincipal(String corUniformePrincipal) {
        this.corUniformePrincipal = corUniformePrincipal;
    }

    public String getGetCorUniformeSecundario() {
        return getCorUniformeSecundario;
    }

    public void setGetCorUniformeSecundario(String getCorUniformeSecundario) {
        this.getCorUniformeSecundario = getCorUniformeSecundario;
    }
}