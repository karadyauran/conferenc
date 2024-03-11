package com.karadyauran.conferenc.error;

public class EmailIsAlreadyTakenException extends RuntimeException
{
    public EmailIsAlreadyTakenException(String message)
    {
        super(message);
    }
}

