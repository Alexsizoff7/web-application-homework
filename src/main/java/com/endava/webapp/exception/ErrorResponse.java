package com.endava.webapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    String message;
    String errorPath;
    int status;
    String httpCodeMessage;
}
