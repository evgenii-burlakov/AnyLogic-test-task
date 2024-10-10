package com.anylogic.taskexecutorservice.exception;

public class ApplicationException extends RuntimeException {
    public ApplicationException(String format, Object... messages) {
        super(String.format(format, messages));
    }

    public ApplicationException(Throwable cause, String format, Object... messages) {
        super(String.format(format, messages), cause);
    }
}
