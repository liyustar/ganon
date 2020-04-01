package com.lyx.ganon.common.exception;

public class GanonCommonException extends RuntimeException {

    public GanonCommonException() {
    }

    public GanonCommonException(String message) {
        super(message);
    }

    public GanonCommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public GanonCommonException(Throwable cause) {
        super(cause);
    }
}
