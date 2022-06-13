package com.mymicro.exceptions;

import lombok.Data;

@Data
public class UserNotFoundException extends RuntimeException {
    private final String message = "User Not Found";
}
