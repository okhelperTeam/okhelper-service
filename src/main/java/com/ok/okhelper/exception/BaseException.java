package com.ok.okhelper.exception;

/**
 * Created by zc on 2017/6/13.
 */
public abstract class BaseException extends Exception {

    public BaseException(String message) {
        super(message);
    }

    public abstract Object getErrorObject();

}
