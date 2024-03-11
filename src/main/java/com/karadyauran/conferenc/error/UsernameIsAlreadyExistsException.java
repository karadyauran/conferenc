package com.karadyauran.conferenc.error;

public class UsernameIsAlreadyExistsException extends RuntimeException
{
    public UsernameIsAlreadyExistsException(String message)
    {
        super(message);
    }
}
