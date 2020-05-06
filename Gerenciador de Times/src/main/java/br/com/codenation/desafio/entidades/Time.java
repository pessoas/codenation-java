package br.com.codenation.desafio.entidades;

import java.time.LocalDate;

public class Time{
    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;

    private Long idCapitao;


    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {

        if(validateTime(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario)){
            this.id = id;
            this.nome = nome;
            this.dataCriacao = dataCriacao;
            this.corUniformePrincipal = corUniformePrincipal;
            this.corUniformeSecundario = corUniformeSecundario;
        }
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

    public String getCorUniformeSecundario() {
        return corUniformeSecundario;
    }

    public void setCorUniformeSecundario(String corUniformeSecundario) {
        this.corUniformeSecundario = corUniformeSecundario;
    }

    public boolean validateTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String getCorUniformeSecundario) {
        if (id == null ||
                nome == null ||
                dataCriacao == null ||
                corUniformePrincipal == null ||
                getCorUniformeSecundario == null) {
            throw new NullPointerException("Argumentos invalidos");
        } else if (id < 0 ||
                nome.trim().isEmpty() ||
                corUniformePrincipal.trim().isEmpty() ||
                getCorUniformeSecundario.trim().isEmpty()) {
            throw new IllegalArgumentException("Parametros invalidos");
        }
        return true;
    }
}