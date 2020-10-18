package com.company;

public interface Impiegato {
    public String getNome() throws Throwable;
    public String getIndirizzo() throws Throwable;
    public int getStipendio() throws Throwable;
    public int aumentaStipendio(int amount) throws Throwable;
}
