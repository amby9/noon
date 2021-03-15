package com.library.management.system.enums;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    INTERNAL_SERVER_ERROR("Something went wrong","Please retry once and let us know if this happens again","Something went wrong"),
    BAD_REQUEST("Bad request","Details are missing/wrong","Details are missing/wrong"),
    MAX_BOOKS_BOOKED_BY_USER("Only 2 books can be booked by user at a time","Only 2 books can be booked by user at a time","Only 2 books can be booked by user at a time"),
    BOOK_ALREADY_BOOKED_BY_ANOTHER_USER("Book with this ID is already booked","Book with this ID is already booked by another user","Book with this ID is already booked by another user"),
    INVALID_BOOK_ID_OR_USER_ID("Wrong details provided","Either book or user ID is wrong","Either book or user ID is wrong"),
    USER_WITH_ID_ALREADY_EXISTS("User already exists","User already exists with this ID","User already exists with this ID"),
    BOOK_WITH_ID_ALREADY_EXISTS("Book already exists","Book already exists with this ID","Book already exists with this ID"),
    CAN_NOT_REMOVE_BOOK_ALREADY_BOOKED_BY_USER("Cannot delete book already booked by user","Book already assigned to user, So can't delete","Book already assigned to user, So can't delete");
    private final String message;
    private final String reason;
    private final String errorDescription;

    ErrorCodes(String message, String reason, String errorDescription){
        this.message = message;
        this.reason = reason;
        this.errorDescription = errorDescription;
    }

    public String getMessage() {
        return message;
    }

    public static ErrorCodes getValue(String errorCode){
        if(errorCode==null)
            return null;
        try {
            return ErrorCodes.valueOf(errorCode);
        }catch(IllegalArgumentException e){
            return null;
        }
    }
}
