package com.lojinha.crud.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BadResourceException extends Exception{

    private List<String> errorMessages = new ArrayList<>();

    public BadResourceException() {
    }

    public BadResourceException(String msg) {
        super(msg);
    }

    public List<String> getErrorMessage(){
        return(errorMessages);
    }

    public void addErrorMessage(String string) {
        // TODO Auto-generated method stub

    }
}