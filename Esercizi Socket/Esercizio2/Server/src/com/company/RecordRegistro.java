package com.company;

import java.io.Serializable;

public class RecordRegistro implements Serializable {
    private static final long serialVersionUID = -1l;

    private String nome;
    private String indirizzo;

    public RecordRegistro(String nome, String indirizzo) {
        this.nome = nome;
        this.indirizzo = indirizzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @Override
    public String toString() {
        return "RecordRegistro{" +
                "nome='" + nome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                '}';
    }
}
