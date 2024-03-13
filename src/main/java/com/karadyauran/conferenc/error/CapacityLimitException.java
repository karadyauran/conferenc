package com.karadyauran.conferenc.error;

public class CapacityLimitException extends RuntimeException
{
    public CapacityLimitException(String message)
    {
        super(message);
    }
}

