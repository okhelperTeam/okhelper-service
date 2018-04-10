package com.ok.okhelper.exception;

public class ForbiddenException extends BaseException {
    public ForbiddenException(String message) {
        super(message);
    }

    @Override
    public Object getErrorObject() {
        return new Exception(this.getMessage());
    }
}
