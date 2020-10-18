package com.company;

public class ImpiegatoServer implements Impiegato {
    private int stipendio;
    private String nome;
    private String indirizzo;

    public ImpiegatoServer(int stipendio, String nome, String indirizzo) {
        this.stipendio = stipendio;
        this.nome = nome;
        this.indirizzo = indirizzo;
    }

    public int getStipendio() {
        return stipendio;
    }

    public String getNome() {
        return nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public int aumentaStipendio(int amount) {
        stipendio = amount > 0 ?  stipendio + amount : stipendio + (amount * -1);
        return stipendio;
    }

    @Override
    public String toString() {
        return "ImpiegatoServer{" +
                "stipendio=" + stipendio +
                ", nome='" + nome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                '}';
    }
}
