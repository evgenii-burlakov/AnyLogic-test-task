package com.anylogic.taskmanagerservice.exception;

public class ApplicationException extends RuntimeException {
    public ApplicationException(String format, Object... messages) {
        super(String.format(format, messages));
    }
}
