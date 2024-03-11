package com.karadyauran.conferenc.error;

public class UsernameIsAlreadyExists extends RuntimeException
{
    public UsernameIsAlreadyExists(String message)
    {
        super(message);
    }
}
