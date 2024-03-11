package com.karadyauran.conferenc.error;

public class SessionWasNotExistsException extends RuntimeException
{
    public SessionWasNotExistsException(String message)
    {
        super(message);
    }
}

