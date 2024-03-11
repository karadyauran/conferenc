package com.karadyauran.conferenc.error;

public class UserIdWasNotFoundException extends RuntimeException
{
    public UserIdWasNotFoundException(String message)
    {
        super(message);
    }
}

