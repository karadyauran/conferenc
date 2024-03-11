package com.karadyauran.conferenc.error;

public class BookingWasNotFoundException extends RuntimeException
{
    public BookingWasNotFoundException(String message)
    {
        super(message);
    }
}

