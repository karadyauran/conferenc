package com.karadyauran.conferenc.error;

public class UsernameWasNotFoundException extends RuntimeException
{
    public UsernameWasNotFoundException(String message)
    {
        super(message);
    }
}
