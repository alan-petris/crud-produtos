package com.exemplo.crud.exception;

public class DadosInvalidosException extends RuntimeException{
    public DadosInvalidosException(String msgError){
        super(msgError);
    }
}
