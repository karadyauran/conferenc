package com.karadyauran.conferenc.error;

public class AlreadyBookedException extends RuntimeException
{
    public AlreadyBookedException(String message)
    {
        super(message);
    }
}

