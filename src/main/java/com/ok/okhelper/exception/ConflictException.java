package com.ok.okhelper.exception;

/**
 * Created by zc on 2017/6/13.
 */
public class ConflictException extends BaseException {
    public ConflictException(String message) {
        super(message);
    }

    @Override
    public Object getErrorObject() {
        return new Exception(this.getMessage());
    }
}
