package com.smu.vaan.exception;

/**
 * Created by vaan on 2017/2/23.
 */
public class ApiException extends Exception {
    private long errCode;
    public ApiException(long errCode, String errMessage) {
        super(errMessage);
        this.errCode = errCode;
    }

    public long getErrCode() {
        return errCode;
    }

    public void setErrCode(long errCode) {
        this.errCode = errCode;
    }

}
