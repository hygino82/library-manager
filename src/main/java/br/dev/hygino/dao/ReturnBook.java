package br.dev.hygino.dao;

public interface ReturnBook {

    boolean changeLoanStatus(long id, boolean status);
}
