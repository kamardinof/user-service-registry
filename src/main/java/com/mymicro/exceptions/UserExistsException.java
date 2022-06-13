package com.mymicro.exceptions;

import lombok.Data;

@Data
public class UserExistsException extends RuntimeException {

    private String message = "User With Email Exists";
}
