package com.karadyauran.conferenc.error;

public class SessionWasNotFoundException extends RuntimeException
{
    public SessionWasNotFoundException(String message)
    {
        super(message);
    }
}

