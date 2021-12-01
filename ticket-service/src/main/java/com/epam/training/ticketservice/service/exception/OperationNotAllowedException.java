package com.epam.training.ticketservice.service.exception;

public class OperationNotAllowedException extends Exception {

    public OperationNotAllowedException(String message){
        super(message);
    }

}
