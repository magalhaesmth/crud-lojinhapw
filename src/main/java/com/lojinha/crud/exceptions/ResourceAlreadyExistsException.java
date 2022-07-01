package com.lojinha.crud.exceptions;

public class ResourceAlreadyExistsException extends Exception{

    public ResourceAlreadyExistsException() {
    }

    public ResourceAlreadyExistsException(String msg) {
        super(msg);
    }
}