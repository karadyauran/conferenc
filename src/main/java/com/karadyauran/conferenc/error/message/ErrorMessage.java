package com.karadyauran.conferenc.error.message;

public class ErrorMessage
{
    public static final String NULL_OR_EMPTY = "OBJECT NULL OR EMPTY";

    // USER
    public static final String USER_ID_WAS_NOT_FOUND = "[USER] ID was not found!";
    public static final String USERNAME_IS_ALREADY_EXISTS = "[USER] Username is already exists!";
    public static final String USERNAME_WAS_NOT_FOUND = "[USER] Username was not found!";
    public static final String EMAIL_IS_ALREADY_TAKEN = "[EMAIL] Email is already taken!";
    public static final String USER_ROLE_IS_NOT_MATCHES = "[USER] Role is not matches";

    // SESSION
    public static final String SESSION_WAS_NOT_FOUND = "[SESSION] Session was not found!";

    // EVENT
    public static final String EVENT_WAS_NOT_FOUND = "[EVENT] Event was not found!";

    // EVENT CATEGORY
    public static final String EVENT_CATEGORY_WAS_NOT_FOUND = "[EVENT CATEGORY] Event category was not found!";

    // BOOKING
    public static final String BOOKING_WAS_NOT_FOUND = "[BOOKING] Booking was not found!";
    public static final String CAPACITY_LIMIT = "[BOOKING] CAPACITY LIMIT";
    public static final String ALREADY_BOOKED = "[BOOKING] Already booked!";
}
